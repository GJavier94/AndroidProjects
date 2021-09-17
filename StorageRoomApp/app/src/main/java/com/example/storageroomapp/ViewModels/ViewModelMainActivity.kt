package com.example.storageroomapp.ViewModels

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.storageroomapp.Constants.Constants
import com.example.storageroomapp.Entities.Book
import com.example.storageroomapp.Entities.User
import com.example.storageroomapp.Models.AppDatabase

class ViewModelMainActivity(application: Application): AndroidViewModel(application) {

    val userList = mutableListOf<User>()
    val bookList = mutableListOf<Book>()

    var dataBase: AppDatabase? = null
    val looper = getApplication<Application>().mainLooper
    val sharePreferences = getApplication<Application>().getSharedPreferences(Constants.DEFAULT_SHARED_PREFERENCES,Context.MODE_PRIVATE)
    val dataBaseIsPopulated:MutableLiveData<Boolean>

    init{
        dataBaseIsPopulated = MutableLiveData<Boolean>(sharePreferences.getBoolean(Constants.DATABASE_POPULATED, false))
    }

    fun populateDatabase() {
        Log.i(TAG, "populateDatabase:Populating database...")
        Log.i(TAG, "populateDatabase:Creating the mutable list of Users...")
        userList.apply {
            add(User(null, "javier", "armenta"))
            add(User(null, "nicolas", "armenta"))
            add(User(null, "ludivina", "garcia" ))
            add(User(null, "pedro", "vera" ))
        }

        bookList.apply {
            add(Book(null, "Angels and demons", 1))
            add(Book(null, "Twilight", 2))
            add(Book(null, "New moon", 3))

        }
        Log.i(TAG, "populateDatabase:Mutable list of Users created...")
        Log.i(TAG, "populateDatabase:Invoking a thread of population...")
        // lets populate the data base with some user values
        Thread(object:Runnable{
            lateinit var handler:Handler
            @RequiresApi(Build.VERSION_CODES.P)
            override fun run() {

                handler = Handler.createAsync(   this@ViewModelMainActivity.looper, Handler.Callback {
                    message ->
                    if(message.what == Constants.THREAD_FINISHED){
                        Log.i(TAG, "The population of the database has finished")
                        val sharedPreferences = getApplication<Application>().getSharedPreferences(Constants.DEFAULT_SHARED_PREFERENCES,Context.MODE_PRIVATE)
                        with(sharedPreferences.edit()){
                            putBoolean(Constants.DATABASE_POPULATED, true )
                        }
                        dataBaseIsPopulated.value = true

                    }
                    true
                })
                populateDB()
            }

            private fun populateDB() {
                Log.i(TAG, "populateDB: getting the DAO " )
                val userDao = dataBase?.userDAO()
                userDao?.insertAll(*userList.toTypedArray()) // we can send an array of data to a vararg argument define in the function
                val bookDao = dataBase?.bookDAO()
                bookDao?.insertAll(*bookList.toTypedArray()) // we can send an array of data to a vararg argument define in the function

                sendMessageFinish()
            }

            private fun sendMessageFinish() {
                val message = handler.obtainMessage().apply {
                    what = Constants.THREAD_FINISHED
                }
                handler.sendMessage(message)
            }

        }).start()


    }

    fun retrieveAllUsers() {
        Thread(object : Runnable {
            override fun run() {
                Log.i(TAG, "retrieving users...")
                val userList = dataBase?.userDAO()?.getAll()
                var logPrintedUsers = "\n\r"
                userList?.forEach {
                        user ->
                    Log.i(TAG, user.toString())
                }
            }
        }).start()

    }

    fun createDataBase(context: Context?) {
        //the dataBaseBuilder method needs  from Room class needs the context and also the name of the
        //kotlin or java class which is the DataBaseClass ( so internally it will create an instance of that class an it will
        // also a name of the database as string needs to be stablished
        //return it
        dataBase = context?.let {
                    context ->
                    Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    Constants.NAME_DATABASE ).build()
        }
        Log.i(TAG,
            if(dataBase != null ) "The database has been created " else "The database is null "
        )


    }

    fun retrieveBooksUser() {
        Thread(object : Runnable {
            override fun run() {
                Log.i(TAG, "retrieving books of some user...")
                val name:String = userList[2].firstName!!
                val bookOwnedList = dataBase?.bookDAO()?.findBooksByNameSync(name)
                var logPrintedUsers = "\n\r"
                bookOwnedList?.forEach {
                        book ->
                    Log.i(TAG, book.toString())
                }
            }
        }).start()

    }

    companion object{
        const val TAG = "ViewModelMainActivityL"
    }
}
