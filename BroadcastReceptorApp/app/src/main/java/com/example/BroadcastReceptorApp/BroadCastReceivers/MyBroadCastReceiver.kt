package com.example.BroadcastReceptorApp.BroadCastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.BroadcastReceptorApp.MainActivity
import java.lang.StringBuilder

class MyBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        /**
         * Broadcast receiver can only execute some tasks that doesn't las that  much
         * because it is registered to the context of a component  ( except from the declared in manifest file)
         *
         */

        Log.i(TAG,"Receiving a Broadcast Message")
        val state = intent?.extras?.get("state")
        StringBuilder().apply {
            append("intent.action = ${intent?.action}")
            append("${intent?.toUri(Intent.URI_INTENT_SCHEME)}")
            toString().apply {
                Log.i(TAG,"Showing the content of the intent $this")
                Toast.makeText( context,"Airplane mode $state", Toast.LENGTH_SHORT ).show()
            }
        }
        Log.i(TAG, Thread.currentThread().name)
    }
    companion object{
        const val TAG = "MyBroadCastReceiverLog"
    }
}