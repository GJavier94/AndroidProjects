package com.example.storageroomapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import com.example.storageroomapp.Constants.Constants
import com.example.storageroomapp.Factory.ViewModelMainFactory
import com.example.storageroomapp.ViewModels.ViewModelMainActivity

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<ViewModelMainActivity>{ ViewModelMainFactory(application) }

    lateinit var  sharedPreference:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreference = this.applicationContext.getSharedPreferences(Constants.DEFAULT_SHARED_PREFERENCES,Context.MODE_PRIVATE)
        with(sharedPreference.edit()){
            putInt("prueba",0)
            commit()
        }

        viewModel.createDataBase(this.applicationContext)

        if(checkFirstLaunch()){
            viewModel.populateDatabase()
        }

        viewModel.dataBaseIsPopulated.observe(this, Observer {
            isDatabasePopulated ->
            if(isDatabasePopulated){

                viewModel.retrieveAllUsers()
                viewModel.retrieveBooksUser()
                viewModel.retrieveAllLibraries()
                viewModel.retrieveAllPlaylists()
                viewModel.retrieveAllSongs()

                viewModel.retrieveUserLibraries()
                viewModel.retrieveUserPlayLists()
                viewModel.retrievePlayListsWithSongs()
                viewModel.retreveSongsWithPlayLists()

                viewModel.retrieveUsersWithPlaylistsAndSongs()
            }
        })


    }

    private fun checkFirstLaunch():Boolean {

        val firstLaunched = sharedPreference.getBoolean(Constants.FIRST_LAUNCHED, true)
        Log.i(TAG, if(firstLaunched) "this is the first launched" else "this is not the first launch")

        if(firstLaunched){
            with(sharedPreference.edit()){
                putBoolean(Constants.FIRST_LAUNCHED, false)
                commit()
            }
        }

        return firstLaunched
    }
    companion object{
        const val TAG = "MainActivityL"
    }

}