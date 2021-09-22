package com.example.databindingapp.viewmodels

import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.databindingapp.MainActivity
import com.example.databindingapp.R
import com.example.databindingapp.Sex
import com.example.databindingapp.databinding.ActivityMainBinding
import com.example.databindingapp.models.User

/**
 * In this case this is the data class to be used on the xml layout activity_main
 */
class ViewModelMainActivity :ViewModel(){

    var mainActivity: MainActivity? = null
    var textViewText = "This is a text in viewModel"
    var numberOfUsers:MutableLiveData<Int> = MutableLiveData(0)

    val userList = MutableLiveData<MutableList<User>>(mutableListOf())
    var lastIndex:MutableLiveData<Int> = MutableLiveData<Int>(0)

    var nameEditText = ""
    var surnameEditText = ""
    var passwordEditText = ""
    var ageEditText = ""

    var binding: ActivityMainBinding? = null
    var sex:MutableLiveData<Int> = MutableLiveData(Sex.MALE)


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


    fun onClickSender(view: View){
        Log.i(TAG, "onClickSender()...called...")
        if(this.mainActivity != null ){
            nameEditText = this.mainActivity?.findViewById<EditText>(R.id.nameEditText)?.text.toString()
            surnameEditText =this.mainActivity?.findViewById<EditText>(R.id.surnameEditText)?.text.toString()
            passwordEditText= this.mainActivity?.findViewById<EditText>(R.id.passwordEditText)?.text.toString()
            ageEditText =this.mainActivity?.findViewById<EditText>(R.id.ageEditText)?.text.toString()

        }

        Log.i(TAG, "onClickSender().. $nameEditText, $surnameEditText, $passwordEditText, $ageEditText")
        if(userList.value != null ){

            Log.i(TAG, "adding the user to the list of users...")
            Toast.makeText(this@ViewModelMainActivity.mainActivity?.applicationContext,"adding the user to the list of users...",Toast.LENGTH_SHORT).show()

            userList.value?.also {
                userList ->
                userList.add(User(nameEditText, surnameEditText, passwordEditText, ageEditText.toInt())).also {
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


    companion object{
        const val TAG = "ViewModelMainActivityL"
    }
}