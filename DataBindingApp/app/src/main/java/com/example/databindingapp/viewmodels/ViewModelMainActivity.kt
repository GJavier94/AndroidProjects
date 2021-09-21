package com.example.databindingapp.viewmodels

import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.databindingapp.MainActivity
import com.example.databindingapp.R
import com.example.databindingapp.databinding.ActivityMainBinding
import com.example.databindingapp.models.User

/**
 * In this case this is the data class to be used on the xml layout activity_main
 */
class ViewModelMainActivity :ViewModel(){

    var mainActivity: MainActivity? = null
    var textViewText = "This is a text in viewModel"
    var numberOfUsers:Int = 0
    val userList = mutableListOf<User>()
    var lastIndex = 0

    var nameEditText = ""
    var surnameEditText = ""
    var passwordEditText = ""
    var ageEditText = ""

    var binding: ActivityMainBinding? = null


    fun onClickSender(view: View){
        Log.i(TAG, "onClickSender()...called...")
        if(this.mainActivity != null ){
            nameEditText = this.mainActivity?.findViewById<EditText>(R.id.nameEditText)?.text.toString()
            surnameEditText =this.mainActivity?.findViewById<EditText>(R.id.surnameEditText)?.text.toString()
            passwordEditText= this.mainActivity?.findViewById<EditText>(R.id.passwordEditText)?.text.toString()
            ageEditText =this.mainActivity?.findViewById<EditText>(R.id.ageEditText)?.text.toString()

        }

        Log.i(TAG, "onClickSender().. $nameEditText, $surnameEditText, $passwordEditText, $ageEditText")
        userList.add(User(nameEditText, surnameEditText, passwordEditText, ageEditText.toInt()))
        lastIndex++
    }


    companion object{
        const val TAG = "ViewModelMainActivityL"
    }
}