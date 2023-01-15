package com.example.databindingapp.viewmodels

import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databindingapp.MainActivity
import com.example.databindingapp.R
import com.example.databindingapp.Sex
import com.example.databindingapp.converters.DateConverter
import com.example.databindingapp.databinding.ActivityMainBinding
import com.example.databindingapp.models.User
import java.util.*

/**
 * In this case this is the data class to be used on the xml layout activity_main
 */
class ViewModelMainActivity :ViewModel(){

    var mainActivity: MainActivity? = null
    var textViewText = "This is a text in viewModel"
    var numberOfUsers:MutableLiveData<Int> = MutableLiveData(0)
    val userList = MutableLiveData<MutableList<User>>(mutableListOf())
    var lastIndex:MutableLiveData<Int> = MutableLiveData<Int>(0)
    var binding: ActivityMainBinding? = null
    var sex:MutableLiveData<Int> = MutableLiveData(Sex.MALE)
    var rememberUser:MutableLiveData<Boolean> = MutableLiveData(false)



    /**
     * In this case it was necessary to declare it as a live data
     * why ? this values are changed by the view component specifically the user
     * so the binding class sets listener when the user  enters new data so that by using two way data binding the ViewModel attr is set with the new value
     * we should use live data in case there are other observers than the view component itself
     */
    var name:String = ""
    var surname:String = ""
    var password:String = ""
    var age: String = ""
    var birthDate: Date = setDefaultDate()

    private fun setDefaultDate(): Date {
        val calendarDate = Calendar.getInstance().apply {
            this.set(Calendar.YEAR, 1990)
            this.set(Calendar.MONTH, 12)
            this.set(Calendar.DAY_OF_MONTH, 24)
        }
        return Date(calendarDate.timeInMillis)
    }

    fun changeSex(view: View,radioButtonId:Int){
        Log.i(TAG, "view:$view, radioButtonId: $radioButtonId")
        when(radioButtonId){
            R.id.femaleSexRadioButton ->{
                sex.value = Sex.FEMALE
            }
            R.id.maleSexRadioButton ->{
                sex.value = Sex.MALE
            }
        }
        Log.i(TAG, "you changed your sex to ${sex.value}")
    }

    fun onClickSender(view:View){

        Log.i(TAG, "onClickSender()...called...")
        Log.i(TAG, "onClickSender().. $name, $surname, $password, $age $birthDate")
        if(userList.value != null ){

            Log.i(TAG, "adding the user to the list of users...")
            Toast.makeText(this@ViewModelMainActivity.mainActivity?.applicationContext,"adding the user to the list of users...",Toast.LENGTH_SHORT).show()

            userList.value?.also {
                    userList ->
                userList.add(User(name, surname, password, age.toInt(), sex.value?:Sex.MALE, birthDate)).also {
                        wasRetrieved ->
                    if(wasRetrieved){
                        lastIndex.value = userList.lastIndex
                        numberOfUsers.value = numberOfUsers.value?.plus(1)
                        Toast.makeText(this@ViewModelMainActivity.mainActivity?.applicationContext, "New user added", Toast.LENGTH_SHORT).show()
                        Log.i(TAG, "New user added")
                    }else{
                        Toast.makeText(this@ViewModelMainActivity.mainActivity?.applicationContext, "Couldn't add a new user into the list of users", Toast.LENGTH_SHORT).show()

                        Log.i(TAG, "Couldn't add a new user into the list of users")

                    }
                }
            }
        }else{
            Log.i(TAG,"Couldn't retrieve list of users")
            Toast.makeText(this.mainActivity?.applicationContext,"Couldn't retrieve list of users", Toast.LENGTH_SHORT).show()
        }

    }

    fun birthDateChange(sequence:CharSequence, start:Int, end:Int, count:Int){
        Log.i(TAG, "$sequence:CharSequence, $start:Int, $end:Int, $count:Int")
        if(sequence.length == 9 ){
            val stringDate = sequence.toString()
             birthDate = DateConverter.stringToDate(stringDate)

        }
    }
    companion object{
        const val TAG = "ViewModelMainActivityL"
    }
}