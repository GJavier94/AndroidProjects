package com.example.storageroomapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import com.example.storageroomapp.Constants.Constants
import com.example.storageroomapp.Factory.ViewModelMainFactory
import com.example.storageroomapp.ViewModels.ViewModelMainActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewRetrieve: TextView
    private lateinit var progressBar: ProgressBar
    val viewModel by viewModels<ViewModelMainActivity>{ ViewModelMainFactory(application) }

    lateinit var  sharedPreference:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        textViewRetrieve = findViewById<TextView>(R.id.textView_retrieving)

        viewModel.retrieveText.observe(this, Observer {
            retrieveText ->
            textViewRetrieve.text = retrieveText
        })

        sharedPreference = this.applicationContext.getSharedPreferences(Constants.DEFAULT_SHARED_PREFERENCES,Context.MODE_PRIVATE)
        with(sharedPreference.edit()){
            putInt("prueba",0)
            commit()
        }
        Log.i(TAG,"dataBaseIsPopulated: observing...")
        viewModel.dataBaseIsPopulated.observe(this, Observer {
                isDatabasePopulated ->
            if(isDatabasePopulated){
                Log.i(TAG,"Database is populated...")
                progressBar.visibility = View.INVISIBLE
                textViewRetrieve.visibility = View.VISIBLE

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


        Log.i(TAG,"createDataBase")
        viewModel.createDataBase(this.applicationContext)

        Log.i(TAG,"checkFirstLaunch")
        if(checkFirstLaunch()){
            Log.i(TAG,"checkFirstLaunch:True.... invoking populateDatabase()")
            viewModel.populateDatabase()
        }




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