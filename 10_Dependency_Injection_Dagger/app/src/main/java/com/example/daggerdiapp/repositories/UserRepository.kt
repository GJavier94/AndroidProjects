package com.example.daggerdiapp.repositories

import android.util.Log
import com.example.daggerdiapp.OnlyOneInstanceScope
import com.example.daggerdiapp.models.UserLocalDataSource
import com.example.daggerdiapp.models.UserRemoteDataSource
import javax.inject.Inject


//@Singleton
@OnlyOneInstanceScope
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
