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
        Log.i(TAG, "Executing a task that cannot guarantee it will finish")
        /**
         * Executing a task that cannot guarantee it will finish
         * For this reason, you should not start long running background threads from a broadcast receiver. After onReceive(), the system can kill the process at any time to reclaim memory,
         * and in doing so, it terminates the spawned thread running in the process.
         *
         * To avoid this a job scheduler is needed or the goAsync method instead
         */
        object:Thread(){
            override fun run() {
                super.run()
                repeat(100){
                    Log.i(TAG, "Hello $it")
                    Thread.sleep(500)
                }
            }
        }.apply { start() }

    }
    companion object{
        const val TAG = "MyBroadCastRPLog"
    }
}
