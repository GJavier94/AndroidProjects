package com.example.daggerdiapp.viewmodels

import android.util.Log
import com.example.daggerdiapp.ActivityScope
import com.example.daggerdiapp.MainActivity
import com.example.daggerdiapp.repositories.UserRepository
import javax.inject.Inject

@ActivityScope
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
){
    val text: String = "hola"

    init {
        Log.i(TAG, "Hi im a LoginViewModel")
        Log.i(TAG, "LoginViewModel hash: ${this.hashCode()}")
        printRepositoryValues()
    }

    private fun printRepositoryValues() {
        Log.i(TAG,"""${this.userRepository.localDataSource.data}
        ${this.userRepository.localDataSource.data}""")
    }

    companion object{

        const val TAG = "LOG:LoginViewModelL"
    }
}
