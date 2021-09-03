package com.example.fragmentlifecycleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

//libraries from fragment in androidx
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "...onCreate...")

        this.supportFragmentManager.commit {
            //This creates the instance then attached it to the fragment Manager
            add<HostFragment>(R.id.fragmentContainerHost, "HostFragment")
            setPrimaryNavigationFragment( supportFragmentManager.findFragmentByTag("HostFragment"))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "...onStart...")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "...onRestoreInstanceState...")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "...onResume...")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "...onPause...")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "...onStop...")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.i(TAG, "...onSaveInstanceState...")
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onDestroy() {
        Log.i(TAG, "...onDestroy...")
        super.onDestroy()
    }

    companion object{
        const val TAG = "MainActivityCLogger"
    }

}