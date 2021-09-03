package com.example.fragmentlifecycleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

//libraries from fragment in androidx
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {

    private lateinit var textViewChangeAct: TextView
    private lateinit var buttonChangeTextView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "...onCreate...")


        textViewChangeAct = findViewById<TextView>(R.id.textViewChangeAct)
        buttonChangeTextView = findViewById<Button>(R.id.buttonChangeTextView)
        buttonChangeTextView.setOnClickListener {
            textViewChangeAct.text = "The text has been changed"
        }
        var buttonGo = findViewById<Button>(R.id.goHostFragment)

        buttonGo.setOnClickListener {
            if( this.supportFragmentManager.findFragmentByTag("HostFragment") == null){

                this.supportFragmentManager.commit {
                    //This creates the instance then attached it to the fragment Manager
                    add<HostFragment>(R.id.fragmentContainerHost, "HostFragment")
                    setPrimaryNavigationFragment( supportFragmentManager.findFragmentByTag("HostFragment"))
                    addToBackStack(null)
                }

            }else{
                Toast.makeText(this@MainActivity, "already opened", Toast.LENGTH_SHORT).show()
            }

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