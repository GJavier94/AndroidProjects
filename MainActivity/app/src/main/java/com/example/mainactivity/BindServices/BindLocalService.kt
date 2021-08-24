package com.example.mainactivity.BindServices

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlin.random.Random

/**
 * If the bindService you are trying to build is only for the app components ( like running some work on the background)
 * then it can be done by
 * creating a class which inherits from IBinder():
 *      creates public methods that:
 *          do some work
 *          return a reference of the service
 *
 * creating an instance of the previous class an returning it onBind()
 * overriding onbind on unbind oncreate and ondestroy
 *
 */

//This service will only just create a random number
class BindLocalService : Service() {
    private val bind:MyBind = MyBind()

    // the class will be public because it will only shared within the app
    inner class MyBind: Binder(){
        fun getService():BindLocalService{
            return this@BindLocalService
        }
    }


    fun getRandomNumber():Int{
        Log.i(TAG, "calling  getRandomNumber")
        Toast.makeText(this,"calling  getRandomNumber", Toast.LENGTH_SHORT )

        return Random.nextInt(0,100)
    }
    override fun onCreate() {
        Log.i(TAG, "creating the service")
        Toast.makeText(this,"creating the service", Toast.LENGTH_SHORT )
    }
    override fun onBind(intent: Intent): IBinder {
        Log.i(TAG, "calling OnBind")
        Toast.makeText(this,"calling OnBind", Toast.LENGTH_SHORT )
        return bind;
    }
    override fun onDestroy() {
        Log.i(TAG, "service unbound...calling on destroy")
        Toast.makeText(this,"onDestroy", Toast.LENGTH_SHORT )
    }


    companion object{
        val TAG = "BindServiceLogger"
    }
}