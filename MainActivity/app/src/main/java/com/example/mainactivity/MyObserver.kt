package com.example.mainactivity

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver: LifecycleObserver {

    init{
        Log.i(TAG, "HI im the observer")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun observerStarting(){
        Log.i(TAG, "ON_START calling observerStarting")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun observerStopping(){
        Log.i(TAG, "ON_STOP calling observerStopping")
    }
    companion object{
        val TAG = "MyObserverLogger"
    }
}