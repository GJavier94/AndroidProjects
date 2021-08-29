package com.example.BroadcastReceptorApp

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.BroadcastReceptorApp.BroadCastReceivers.MyBroadCastReceiverProgramatic

class MainActivity : AppCompatActivity() {
    private lateinit var  br: MyBroadCastReceiverProgramatic
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.TextView)
        textView.text = "null"
        Log.i(TAG, Thread.currentThread().name)

        //Declaring a programmatic broadCast Receiver
        br = MyBroadCastReceiverProgramatic()

        this.registerReceiver(br, IntentFilter(Intent.ACTION_HEADSET_PLUG))


    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(br)
    }
    companion object{
        const val TAG = "MainActivityLog"
    }
}