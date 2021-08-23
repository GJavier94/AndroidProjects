package com.example.mainactivity

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver(private val lifecycle:Lifecycle): LifecycleObserver {
    private var enabled = false

    init{
        Log.i(TAG, "HI im the observer")
        this.enable()
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun observerStarting(){
        // this will be called only if the other methods have finished by using a flag variable
        if(enabled){
            Log.i(TAG, "ON_START calling observerStarting")
        }
    }

    fun enable() {
        enabled = true
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            Log.i(TAG, "STARTED calling observerStarting")
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun observerStopping(){
        Log.i(TAG, "ON_STOP calling observerStopping")
    }
    companion object{
        val TAG = "MyObserverLogger"
    }
}