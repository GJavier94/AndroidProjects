package com.example.daggerdiapp.repositories

import android.util.Log
import com.example.daggerdiapp.MyCustomScope
import com.example.daggerdiapp.models.UserLocalDataSource
import com.example.daggerdiapp.models.UserRemoteDataSource
import javax.inject.Inject
import javax.inject.Scope
import javax.inject.Singleton


//@Singleton
@MyCustomScope
class UserRepository @Inject constructor(
    val localDataSource: UserLocalDataSource,
    val remoteDataSource: UserRemoteDataSource){

    init {
        Log.i(TAG, "Initializing UserRepository")
    }

    companion object {
        const val TAG = "LOG:UserRepositoryL"
    }
}
