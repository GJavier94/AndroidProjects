package com.example.BroadcastReceptorApp.BroadCastReceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import org.chromium.base.Log

class MyBroadCastReceiverProgramatic: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
         Log.i(TAG,"Event Headset..")
        StringBuilder().apply {
            append("intent.action = ${intent?.action}")
            append("${intent?.toUri(Intent.URI_INTENT_SCHEME)}")
            toString().apply {
                Log.i(TAG,"Showing the content of the intent $this")
                Toast.makeText( context,"Airplane mode ", Toast.LENGTH_SHORT ).show()
            }
        }
        Log.i(TAG, Thread.currentThread().name)
    }
    companion object{
        const val TAG = "MyBroadCastRPLog"
    }
}
