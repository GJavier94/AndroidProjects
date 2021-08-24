package com.example.mainactivity.BindServicesMessaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.os.Messenger
import com.example.mainactivity.BindServices.BindLocalService
import com.example.mainactivity.R

class MessagingClientActivity : AppCompatActivity() {

    private lateinit var buttonStartMessagingService: Button
    private var mBound: Boolean = false

    //we need to create a ref to the service and a connection object channel
    private lateinit var mService: Messenger
    private var connection:ServiceConnection = object : ServiceConnection{
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


    }
    fun sendMessage(view: View){
        if(mBound){

        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "calling on Start")
        val intent = Intent(this, BindServiceMessenger::class.java)

        Log.i(TAG, "binding the $TAG  to service")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
    companion object{
        val TAG = "ClientActivity2Logger"
    }




}



import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class ClientActivity : AppCompatActivity() {

    private  val connection:ServiceConnection = object:ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BindLocalService.MyBind
            myService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }

    }



}