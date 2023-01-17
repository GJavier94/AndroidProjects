package com.example.mainactivity.BindServicesMessaging

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

class BindServiceMessenger : Service() {
    inner class MyHandler: Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                MSG_HELLO -> {
                    val messageText:String = (msg.obj as Bundle).getString("MESSAGE") ?: "unknonwn"
                    Log.i(TAG,messageText )
                }
            }
        }
    }

    override fun onCreate() {
        Log.i(TAG, "Creating the service $TAG")
    }
    override fun onBind(intent: Intent): IBinder {
        val ClientID:String = intent.getStringExtra("ClientID") ?: "unknown"
        Log.i(TAG, "Binding to $ClientID")
        val messengerObj = Messenger(MyHandler())
        return messengerObj.binder

    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "All the services have been unbind")
        return false
    }
    override fun onDestroy() {
        Log.i(TAG, "Destroying the service $TAG")
    }
    companion object{
        val TAG = "BindServMsnLogger"
        val MSG_HELLO = 1
    }
}