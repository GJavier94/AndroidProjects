package com.example.BroadcastReceptorApp.BroadCastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.BroadcastReceptorApp.MyService

class LocalBroadcastReceiv() : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG,"Receiving a Broadcast Message")

        StringBuilder().apply {
            append("intent.action = ${intent?.action}")
            append("${intent?.toUri(Intent.URI_INTENT_SCHEME)}")
            toString().apply {
                Log.i(TAG,"Showing the content of the intent $this")
            }
        }
        Log.i(TAG,"Trying to stop the service...")
        context?.stopService(Intent(context, MyService::class.java))
    }
    companion object{
        const val TAG = "LocalBroadcastReceiv"
    }
}