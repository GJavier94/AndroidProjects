package com.example.BroadcastReceptorApp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Creating the service ")
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Starting a service ")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Destroying service...")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object{
        const val TAG = "MyServiceLog"
    }
}