package com.example.BroadcastReceptorApp

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.BroadcastReceptorApp.BroadCastReceivers.LocalBroadcastReceiv
import com.example.BroadcastReceptorApp.BroadCastReceivers.MyBroadCastReceiverProgramatic

class MainActivity : AppCompatActivity() {
    private lateinit var  br: MyBroadCastReceiverProgramatic
    private lateinit var  localBroadcastReceiver: LocalBroadcastReceiv
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView = findViewById<TextView>(R.id.TextView)
        textView.text = "null"
        Log.i(TAG, Thread.currentThread().name)


        //Declaring a programmatic broadCast Receiver
        br = MyBroadCastReceiverProgramatic()
        this.registerReceiver(br, IntentFilter(Intent.ACTION_HEADSET_PLUG))

        /**
        * in order to show how local broadcast work
        * 1.- start a service in the MainActivity
        * 2.-  create a  broadcast receiver register to the context of the act
        * 3.- send a message fro the act to the receiver to finish the service
        * */
        Log.i(TAG, "Trying to start a service..")
        val nameService = this.startService(Intent(this,MyService::class.java))
        localBroadcastReceiver = LocalBroadcastReceiv()
        LocalBroadcastManager.getInstance(this).registerReceiver( localBroadcastReceiver, IntentFilter( BroadCastMessages.TERMINAR_LOCAL_BROADCAST ))

        findViewById<Button>(R.id.button).setOnClickListener {
            Log.i(TAG, "Sending the local broadcast..")
            LocalBroadcastManager.getInstance(this).
            sendBroadcast(
                Intent(BroadCastMessages.TERMINAR_LOCAL_BROADCAST).apply {
                    putExtra("variable", "This message is to terminate the service")
                }
            )
        }
        findViewById<Button>(R.id.buttonStartAct).setOnClickListener {
            Log.i(TAG, "Starting act broadcast")
            Intent(this, senderBMActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(br)
        LocalBroadcastManager.getInstance(this).unregisterReceiver( localBroadcastReceiver)
    }
    companion object{
        const val TAG = "MainActivityLog"
    }
}