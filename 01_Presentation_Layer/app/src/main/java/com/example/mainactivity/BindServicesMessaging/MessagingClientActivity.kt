package com.example.mainactivity.BindServicesMessaging

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.mainactivity.R

class MessagingClientActivity : AppCompatActivity() {

    private lateinit var buttonStartMessagingService: Button
    private var mBound: Boolean = false

    //we need to create a ref to the service and a connection object channel
    private lateinit var mService: Messenger
    private var connection: ServiceConnection = object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client2)
        buttonStartMessagingService = findViewById<Button>(R.id.buttonStartMessagingService)
        val intent = Intent(this, BindServiceMessenger::class.java).apply {
            putExtra( "ClientID", TAG)
        }
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        Log.i(TAG, "binding the $TAG  to service")
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG, "calling on Start")

    }

    override fun onDestroy() {
        super.onDestroy()
        if(mBound){
            unbindService(connection)
        }
    }
    fun sendMessage(view: View){
        // we will neeed the message class to build an Message Object
        if(mBound){
            val msg = Message.obtain(null,MSG_HELLO, 0,0,
                Bundle().apply {
                    putString("MESSAGE",  "Hey what's up im calling from client $TAG")
                }
                )
            Toast.makeText(this,"Sending message to service ", Toast.LENGTH_SHORT).show()
            mService.send(msg)
        }
    }

    companion object{
        val TAG = "ClientActivity2Logger"
        val MSG_HELLO = 1
    }

}


