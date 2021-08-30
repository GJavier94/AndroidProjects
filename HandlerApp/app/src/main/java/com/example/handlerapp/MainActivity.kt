package com.example.handlerapp

import android.os.*
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private  lateinit var buttonSendMessage: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * We will create two threads so that each thread runs with the UIThread as a parent
         * each thread will have one handler
         * the handler defines a method which will handle the message in the other thread
         * the handler will be responsible for getting the message from the queue and executingthem
         */
        Log.i("HandlerThread_1", "Creating the thread...")
        val h1 = object:HandlerThread("HandlerThread_1"){
            override fun run() {
                super.run()
                Log.i(TAG , "running....")
            }
        }
        h1.start()



        val h2 = object:HandlerThread("HandlerThread_2"){
            val handler2 = object:Handler(h1.looper){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    Log.i(TAG , "this code is executed in HandlerThread_1 ${msg.arg1}" )
                }
            }
        }
        h2.start()

        val handlerUIthread = object:Handler(h2.looper){
            override fun handleMessage(msg: Message) {
                super.handleMessage(msg)
                Log.i(TAG , "this code is executed in  handlerUIthread_2")
                Log.i(TAG, "sending message to handlerUIthread_2")
                val cont:Int = msg.arg1 + 1
                val msg  = h2.handler2.obtainMessage()
                msg.arg1 = cont
                h2.handler2.sendMessage(msg)

            }
        }
        val cont:Int = 1
        findViewById<Button>(R.id.buttonSendMessage).setOnClickListener {
            Log.i(TAG, "This code is executed in UiThread ")
            val msg = handlerUIthread.obtainMessage()
            msg.arg1 = cont
            handlerUIthread.sendMessage(msg)

        }

    }
    companion object{
        const val TAG = "MainActivityLog"
    }
}





