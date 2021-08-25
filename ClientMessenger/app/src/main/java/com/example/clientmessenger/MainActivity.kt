package com.example.clientmessenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var buttonStartMessagingService: Button
    private var mBound: Boolean = false

    //we need to create a ref to the service and a connection object channel
    private lateinit var mService: Messenger
    private var connection: ServiceConnection = object : ServiceConnection {
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
        setContentView(R.layout.activity_main)

        buttonStartMessagingService = findViewById<Button>(R.id.buttonStartMessagingService)
        val intent = Intent().apply {
            component = ComponentName("com.example.mainactivity", "com.example.mainactivity.BindServicesMessaging.BindServiceMessenger")
            putExtra( "ClientID", TAG)
        }
        Log.i(TAG, "binding the $TAG  to service")
        val success =  bindService(intent, connection, Context.BIND_AUTO_CREATE)
        if(!success){
            Toast.makeText(this,"Couldn't bind to the service", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "Couldn't bind to the service")
        }else{
            Toast.makeText(this," bound to the service", Toast.LENGTH_SHORT).show()
            Log.i(TAG, " bound to the service")
        }
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
            Toast.makeText(this,"Sending message to service ", Toast.LENGTH_SHORT).show()
            val msg = Message.obtain(null,MSG_HELLO, 0,0,
                Bundle().apply {
                    putString("MESSAGE",  "Hey what's up im calling from client $TAG")
                }
            )
            try {
                mService.send(msg)
            } catch (e: RemoteException) {
                Log.i(TAG, "Can't send msg to service");
                e.printStackTrace()
            }

        }
    }
    companion object{
        val TAG = "clientmsn.MActivity"
        val MSG_HELLO = 1
    }
}