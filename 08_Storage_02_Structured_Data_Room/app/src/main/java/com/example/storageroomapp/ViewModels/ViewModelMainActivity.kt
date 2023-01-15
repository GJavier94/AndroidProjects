package com.example.storageroomapp.ViewModels

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.storageroomapp.Constants.Constants
import com.example.storageroomapp.Entities.*
import com.example.storageroomapp.Entities.Relationships.PlayListWithSongs
import com.example.storageroomapp.Entities.Relationships.SongWithPlayLists
import com.example.storageroomapp.Entities.Relationships.UserAndLibrary
import com.example.storageroomapp.Entities.Relationships.UserWithPlayListsAndSongs
import com.example.storageroomapp.Models.AppDatabase
import kotlinx.coroutines.*
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext

class ViewModelMainActivity(application: Application): AndroidViewModel(application) {

    val retrieveText: MutableLiveData<String> = MutableLiveData("")

    val userList = mutableListOf<User>()
    val bookList = mutableListOf<Book>()
    val libraryList = mutableListOf<Library>()
    val playListList = mutableListOf<PlayList>()
    val songList = mutableListOf<Song>()
    val playListsSongsCrossRefList = mutableListOf<PlaylistSongCrossRef>()
    private val context: CoroutineContext = Dispatchers.Main




    var dataBase: AppDatabase? = null
    val sharePreferences = getApplication<Application>().getSharedPreferences(Constants.DEFAULT_SHARED_PREFERENCES,Context.MODE_PRIVATE)
    val dataBaseIsPopulated:MutableLiveData<Boolean>

    init{
        dataBaseIsPopulated = MutableLiveData<Boolean>(sharePreferences.getBoolean(Constants.DATABASE_POPULATED, false))
        Log.i(TAG, "dataBaseIsPopulated:${dataBaseIsPopulated.value} ")
    }

    fun populateDatabase() {
        Log.i(TAG, "populateDatabase:Populating database...")
        Log.i(TAG, "populateDatabase:Creating the mutable list of Users...")
        userList.apply {
            add(User(1, "javier", "armenta"))
            add(User(2, "nicolas", "armenta"))
            add(User(3, "ludivina", "garcia" ))
            add(User(4, "pedro", "vera" ))
        }
        Log.i(TAG, "populateDatabase:Mutable list of Users created...")
        Log.i(TAG, "populateDatabase:Creating the mutable list of Books...")
        bookList.apply {
            add(Book(1, "Angels and demons", 1))
            add(Book(2, "Twilight", 2))
            add(Book(3, "New moon", 3))

        }
        Log.i(TAG, "populateDatabase:Mutable list of Books created...")
        Log.i(TAG, "populateDatabase:Creating the mutable list of Libraries...")
        libraryList.apply {
            add(Library(1,1))
            add(Library(2,2))
            add(Library(3,3))
           // add(Library(4,4))
        }
        Log.i(TAG, "populateDatabase:Mutable list of Libraries created...")

        Log.i(TAG, "populateDatabase:Mutable list of PlayList created...")
        Log.i(TAG, "populateDatabase:Creating the mutable list of PlayList...")
        playListList.apply {
            add(PlayList(1, "favorites", 1))
            add(PlayList(2, "urban music", 1))
            add(PlayList(3, "traditional mexican music", 2))
            add(PlayList(4, "80s decade music", 3))
            add(PlayList(5, "pop usa music", 3))

        }
        Log.i(TAG, "populateDatabase:Mutable list of PlayList created...")

        Log.i(TAG, "populateDatabase:Mutable list of Songs created...")
        Log.i(TAG, "populateDatabase:Creating the mutable list of Songs...")
        songList.apply {
            add(Song(1,"circles","post malone"))
            add(Song(2,"Peaches","Justin Bieber"))
            add(Song(3,"higher power","Coldplay"))
            add(Song(4,"solar power","Lorde"))
            add(Song(5,"electric","Katy perry"))
            add(Song(6,"lost","Maroon 5"))
            add(Song(7,"Dont start now","Dua lipa"))
            add(Song(8,"Problablemente","Cristian Nodal"))
            add(Song(9,"Cuando calienta el sol","Luis Miguel"))
            add(Song(10,"4 babies","Maluma"))
        }
        Log.i(TAG, "populateDatabase:Mutable list of Songs created...")

        Log.i(TAG, "populateDatabase:Mutable list of playListsSongsCrossRefList created...")
        Log.i(TAG, "populateDatabase:Creating the mutable list of playListsSongsCrossRefList...")
        playListsSongsCrossRefList.apply {
            add( PlaylistSongCrossRef(1, 1))
            add( PlaylistSongCrossRef(1, 6))
            add( PlaylistSongCrossRef(1, 8))
            add( PlaylistSongCrossRef(2, 10))
            add( PlaylistSongCrossRef(3, 8))
            add( PlaylistSongCrossRef(3, 9))
            add( PlaylistSongCrossRef(5, 2))
            add( PlaylistSongCrossRef(5, 3))
            add( PlaylistSongCrossRef(5, 4))
            add( PlaylistSongCrossRef(5, 5))
            add( PlaylistSongCrossRef(5, 6))
            add( PlaylistSongCrossRef(5, 7))

        }
        Log.i(TAG, "populateDatabase:Mutable list of playListsSongsCrossRefList created...")




        Log.i(TAG, "populateDatabase:Invoking a thread of population...")


        viewModelScope.launch{
            Log.i(TAG, "Coroutine scope with value: ${this.hashCode()}")
            Log.i(TAG, "The scope is in ${Thread.currentThread().name}")

            val job = launch(Dispatchers.Default) {
                Log.i(TAG, "Coroutine scope with value: ${this.hashCode()}")
                Log.i(TAG, "running populateDatabase inner coroutine in ${Thread.currentThread().name}")

                populateDB()
                Log.i(TAG, "The population of the database has finished")
                val sharedPreferences = getApplication<Application>().getSharedPreferences(Constants.DEFAULT_SHARED_PREFERENCES,Context.MODE_PRIVATE)
                with(sharedPreferences.edit()){
                    putBoolean(Constants.DATABASE_POPULATED, true )
                    commit()
                }
                delay(5000L)
            }
            Log.i(TAG,"the job of population has been launched...")
            job.join()
            this@ViewModelMainActivity.dataBaseIsPopulated.value = true
            Log.i(TAG," Hola ${this@ViewModelMainActivity.dataBaseIsPopulated.value}")

        }
    }

    private suspend fun populateDB() {
        // this function is run into a coroutine
        Log.i(TAG, "populateDB: getting the userDAO " )
        val userDao = dataBase?.userDAO()
        userDao?.insertAll(*userList.toTypedArray()) // we can send an array of data to a vararg argument define in the function

        Log.i(TAG, "populateDB: getting the bookDAO " )
        val bookDao = dataBase?.bookDAO()
        bookDao?.insertAll(*bookList.toTypedArray()) // we can send an array of data to a vararg argument define in the function

        Log.i(TAG, "populateDB: getting the libraryDAO " )
        val libraryDAO = dataBase?.libraryDAO()
        libraryDAO?.insertAll(*libraryList.toTypedArray()) // we can send an array of data to a vararg argument define in the function

        Log.i(TAG, "populateDB: getting the playListDAO " )
        val playListDAO = dataBase?.playListDAO()
        playListDAO?.insertAll(*playListList.toTypedArray()) // we can send an array of data to a vararg argument define in the function

        Log.i(TAG, "populateDB: getting the playListDAO " )
        val songDAO = dataBase?.songDAO()
        songDAO?.insertAll(*songList.toTypedArray()) //


        Log.i(TAG, "populateDB: getting the playListsSongsCrossRefDAO " )
        val playListSongCrossRefDAO = dataBase?.playListSongCrossRefDAO()
        playListSongCrossRefDAO?.insertAll(*playListsSongsCrossRefList.toTypedArray()) //
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

    fun retrieveAllUsers() {
        var retrievedData:String = ""
        viewModelScope.launch {
            val job = launch(Dispatchers.Default){
                Log.i(TAG, "retrieving users...")
                val userList = dataBase?.userDAO()?.getAll()

                userList?.forEach {
                        user ->
                    Log.i(TAG, user.toString())
                    retrievedData = retrievedData.plus(user.toString())
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }
    }

    fun retrieveBooksUser() {

        viewModelScope.launch {
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default){
                Log.i(TAG, "retrieving books of some user...")
                if(userList.size > 0 ){
                    val name:String = userList[2].firstName
                    val bookOwnedList = dataBase?.bookDAO()?.findBooksByNameSync(name)
                    bookOwnedList?.forEach {
                            book ->
                        Log.i(TAG, book.toString())
                        retrievedData = retrievedData.plus(book.toString())
                    }
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }



    }

    fun retrieveAllLibraries() {
        viewModelScope.launch {
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default) {
                Log.i(TAG, "retrieving all libraries  ...")

                val librariesList = dataBase?.libraryDAO()?.getAll()
                librariesList?.forEach {
                        library ->
                    Log.i(TAG, """retrieveAllLibraries:$library""")
                    retrievedData = retrievedData.plus("""retrieveAllLibraries:$library""")

                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }
    }

    fun retrieveAllPlaylists() {
        viewModelScope.launch{
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default){
                Log.i(TAG, "retrieving all Playlists  ...")

                val playListList = dataBase?.playListDAO()?.getAll()
                playListList?.forEach {
                        playList ->
                    Log.i(TAG, """retrieveAllLibraries:$playList""")
                    retrievedData = retrievedData.plus("""retrieveAllLibraries:$playList""")
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }
    }


    fun retrieveAllSongs() {
        viewModelScope.launch{
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default){
                Log.i(TAG, "retrieving all Songs  ...")

                val songList = dataBase?.songDAO()?.getAll()
                songList?.forEach {
                        song ->
                    Log.i(TAG, """retrieveAllSongs:$song""")
                    retrievedData = retrievedData.plus("""retrieveAllSongs:$song""")
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }
    }

    /**
     * This uses one to one relationship by creating a new holder data class
     * which established the relationship  the two entities and the fields with they are associated
     * in this case User -parent  Library-child the  child holds the primary key of the parent as its foreign key
     * The dao object makes this query as a transaction to join two tables and in the query just uses the parent
     *
     * it's important to check which object is the parent cause in case the relationship can be 1:(0,1) the child can be null
     *
     */

    fun retrieveUserLibraries() {
        viewModelScope.launch{
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default){
                Log.i(TAG, "retrieving table users and libraries of all users...")
                val libraryUserList: List<UserAndLibrary>?  = dataBase?.libraryDAO()?.getUsersAndLibraries()
                if(libraryUserList != null){
                    libraryUserList.forEach {
                            libraryUser ->
                        Log.i(TAG, """retrieveUserLibraries:${libraryUser}""")
                        retrievedData = retrievedData.plus("""retrieveUserLibraries:${libraryUser}""")
                    }
                }else{
                    Log.i(TAG, "retrieveUserLibraries: the libraryUserList is null")
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }
    }

    /**
     * One to many relationships the parent is the one with cardinality one
     * and the child is the one with cardinality many because it will hold the primary key of the parent as a foreign key
     *
     * IT is necessary to also define a data holder class wich defines the relationship but in this case
     * instead of a single instance of the child we will declare a collection of instances of the child
     * example  User -> parent
     *          List<PlayList>? -> Child       1parent -> (0:many) childs also it can be null
     *
     * Also the query on the DAO object is just calling the Parent table an returning a list of the data holder relationship class -> UserAndPlayList
     */
    fun retrieveUserPlayLists() {
        viewModelScope.launch{
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default){
                Log.i(TAG, "retrieving table User PlayLists of all users...")
                val libraryUserPlayList: List<UserAndPlayList>?  = dataBase?.playListDAO()?.getUsersAndPlayLists()
                if(libraryUserPlayList != null){
                    libraryUserPlayList.forEach {
                            libraryPlayList ->
                        Log.i(TAG, """retrieveUserPlayLists:${libraryPlayList.user} and its user playlist are: ${
                            libraryPlayList.playlistList?.joinToString("-") { it.toString() }}}""")
                        retrievedData = retrievedData.plus("""retrieveUserPlayLists:${libraryPlayList.user} and its user playlist are: ${
                            libraryPlayList.playlistList?.joinToString("-") { it.toString() }}}""")

                    }
                }else{
                    Log.i(TAG, "retrieveUserPlayLists: the libraryUserPlayList is null")
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }
    }

    /** for many to many relationships
     * between two tables: the solution is to create new entity table wich holds as primary key a composite primary key which composed by two foreign keys wich are pointing
     *          to the primary keys of the two tables
     *
     * So there are two Parents
     * table1 M:M table2  so table1-> parent table2 -> parent
     *        crossRefTable  which is a child
     *
     *         * table1 M:1  crossRefTable ->  is the child
     *         * table2 M:1  crossRefTable ->  is the child
     * This implies that the crossRefTable will be an entity table persisted in SQLite
     * Then because it is also a table we need
     *              1- create a list playListsSongsCrossRefList to populated this relational table
     *              2.- create a fun in appDataBase to return a DAO of this table
     *              3.- create a DAO for this table to omake crud Operations
     *              4.- create this class as and @Entity class defining its foreign columns with on delete
     *
     *              5.- Create dataHolder class to hold the relation with one parent table
     *              6.- create dataHolder class to hold the other relation with the other parent table
     *
     *              7.- define the new method into a DAO to retrieve data joined in one parent child relationship
     *              8.- define the new method into the other DAO to retrieve data joined in the other parent child relationship
     *
     *              Room does it for you the junction within the relationship by defining a
     *              param ->associateBy -> wich needs a Junction object (which wants the other parent ) to make the join
     *
     */

    fun retrievePlayListsWithSongs() {
        viewModelScope.launch{
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default){
                Log.i(TAG, "retrieving table PlayLists With Songs of each PlayList...")
                val playListWithSongsList: List<PlayListWithSongs>?  = dataBase?.playListDAO()?.getPlayListsWithSongs()
                if(playListWithSongsList != null){
                    playListWithSongsList.forEach {
                            playListWithSongs ->
                        Log.i(TAG, """retrievePlayListsWithSongs:${playListWithSongs.playList} and its user playlist are: ${
                            playListWithSongs.songs?.joinToString("-") { it.toString() }}}""")

                        retrievedData = retrievedData.plus("""retrievePlayListsWithSongs:${playListWithSongs.playList} and its user playlist are: ${
                            playListWithSongs.songs?.joinToString("-") { it.toString() }}}""")
                    }
                }else{
                    Log.i(TAG, "retrievePlayListsWithSongs: the playListWithSongsList is null")
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }


    }

    fun retreveSongsWithPlayLists() {
        viewModelScope.launch{
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default){

                Log.i(TAG, "retrieving table Songs With PlayLists of each Song...")
                val songWithPlayListsList: List<SongWithPlayLists>?  = dataBase?.songDAO()?.getSongsWithPlayLists()
                if(songWithPlayListsList != null){
                    songWithPlayListsList.forEach {
                            songWithPlayLists ->
                        Log.i(TAG, """retreveSongsWithPlayLists:${songWithPlayLists.song} and its user playlist are: ${
                            songWithPlayLists.playList?.joinToString("-") { it.toString() }}}""")

                        retrievedData = retrievedData.plus("""retreveSongsWithPlayLists:${songWithPlayLists.song} and its user playlist are: ${
                            songWithPlayLists.playList?.joinToString("-") { it.toString() }}}""")
                    }
                }else{
                    Log.i(TAG, "retreveSongsWithPlayLists: the songWithPlayListsList is null")
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }

    }

    /**
     * Nested Relationship
     * in case we want to make a join of multiple tables
     * we can use the data holder relationship class to declare the relation in recursive way
     * relationship
     *      entity
     *      relation()
     *      another relation ... and so on !
     * for example we can join Users Playlist Songs
     * with a data holder class  UsersWithPlayListsAndSongs
     * then maked this  data holder relationship class holds relationship  PlaylistAndSongs
     *
     */

    fun retrieveUsersWithPlaylistsAndSongs() {
        viewModelScope.launch{
            var retrievedData:String = ""
            val job = launch(Dispatchers.Default){
                Log.i(TAG, "retrieving table Users With Playlists And Songs of each User...")
                val userWithPlayListsAndSongsList: List<UserWithPlayListsAndSongs>?  = dataBase?.userDAO()?.getAllUsersWithPlayListsAndSongs()
                if(userWithPlayListsAndSongsList != null){
                    userWithPlayListsAndSongsList.forEach {
                            userWithPlayListsAndSongs ->
                        Log.i(TAG, """retrieveUsersWithPlaylistsAndSongs:
                            ${userWithPlayListsAndSongs.user} 
                            ${userWithPlayListsAndSongs.playLists?.joinToString("\n\r") {
                                playListWithSongs ->
                            playListWithSongs.playList.toString().plus("\n\r").plus(
                                playListWithSongs.songs?.joinToString("-"){it.toString()}
                            )}
                        }""")

                        retrievedData = retrievedData.plus("""retrieveUsersWithPlaylistsAndSongs:
                            ${userWithPlayListsAndSongs.user} 
                            ${userWithPlayListsAndSongs.playLists?.joinToString("\n\r") {
                                playListWithSongs ->
                            playListWithSongs.playList.toString().plus("\n\r").plus(
                                playListWithSongs.songs?.joinToString("-"){it.toString()}
                            )}
                        }""")

                    }
                }else{
                    Log.i(TAG, "retrieveUsersWithPlaylistsAndSongs: the userWithPlayListsAndSongsList is null")
                }
            }
            job.join()
            retrieveText.value = retrieveText.value?.plus(retrievedData)
        }

    }


    companion object{
        const val TAG = "ViewModelMainActivityL"
    }
}
