package com.example.BroadcastReceptorApp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class senderBMActivity : AppCompatActivity() {

    private lateinit var br1: BroadcastReceiver

    private lateinit var br2: BroadcastReceiver
    private lateinit var br3: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender_bmactivity)
        br1 = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.i("BroadCastRecOrdered", "Receiver priority 1")
            }
        }
        br2 = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.i("BroadCastRecOrdered", "Receiver priority 2")
            }
        }

        br3 = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.i("BroadCastRecOrdered", "Receiver priority 3")
            }
        }
        this.registerReceiver(br1,
            IntentFilter(ACTION_SEND_BROADCAST).apply {
                priority = LOW_PRIORITY
            }
        )
        this.registerReceiver(br2,
            IntentFilter(ACTION_SEND_BROADCAST).apply {
                priority = MEDIUM_PRIORITY
            }
        )
        this.registerReceiver(br3,
            IntentFilter(ACTION_SEND_BROADCAST).apply {
                priority = HIGH_PRIORITY
            }
        )
        findViewById<Button>(R.id.buttonSendMessage).setOnClickListener {
            //here is where we send a broadcast message with priorities
            //using sendOrderBroadcast
            //the intent it is only necessary the action to identify the message and the intent
            // so that only registerd receiver can receive it
            Log.i(TAG, "sending message order broadcast")
            this.sendOrderedBroadcast(
                Intent().apply {
                     action = ACTION_SEND_BROADCAST
                     putExtra("entero", 1)
                }
                ,null
            )

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.unregisterReceiver(br1)
        this.unregisterReceiver(br2)
        this.unregisterReceiver(br3)
    }
    companion object{
        const val TAG = "senderBMActivityLog"
        const val ACTION_SEND_BROADCAST = "com.example.BroadcastReceptorApp.ACTION_SEND_BROADCAST"
        const val PERMISSION_SEND_BROADCAST = "com.example.BroadcastReceptorApp.PERMISSION_SEND_BROADCAST"
        const val LOW_PRIORITY = 0
        const val MEDIUM_PRIORITY = 1
        const val HIGH_PRIORITY = 2
    }
}
