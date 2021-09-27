package com.example.daggerdiapp.models

import android.util.Log
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(){
    val data = "Hi im UserLocalDataSource"

    init {
        Log.i(TAG, "$data" )
    }
    companion object{
        const val TAG = "LOG:UserLocalDataSL"
    }
}