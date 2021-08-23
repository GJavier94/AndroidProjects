package com.example.mainactivity.Processing

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

/**
 * A service is an entry point which doesn't have User interface
 * it's of general purpose, it is made to execute work whatever the app is ( foregroun or background)
 * The service will continue be running
 * They can be created by any other app component
 *
 * There are two types of services
 * 1.- Started services: they are started by a component and will continue be running until it stops itself or another component
 *      background: Those services which run without the awareness of the user: Example downloading sth
 *      foreground: Those services which run with the awareness of the user: Example playing a song in a stream music app
 * 2.- bind Services: services which the app component is bound to
 *                  it will stop if none any other component is bound to the app
 * 3.- mixture: as long as the service implements the methods of the 1) and 2) it can behave as both
 *
 * Services vs Threads
 * A services run's on ui Thread, if the amount of work is heavy another thread within the service should be created
 * A Thread can runs independently from the main thread but it's lifecycle is bounded to an activit or component
 * if and act is in the foreground with onresume -> then the thread would be on run()
 * but if the act comes to the background onstop -> then the thread would be stop()
 *
 * Meanwhile a service runs completely independently
        * if and act is in the foreground with onresume -> then the service would be running
        * but if the act comes to the background onstop -> then the thread would be also running
 *
 */

/**
 * In this case we will create a started service who manage tasks in a queued way (serially and in order)
 *
 * Description:
 * A basic Thread java object once started and when executing method run(), it executes the code there and once
 * it finishes it is stopped and destroyed
 *
 * it's lifecycle is             sleep done,i/o complete
 *                                resume, notify, notifyall
 *
 * new         runnable   running          non runnable          dead
 *     start                    sleep,locked,
 *                              suspend
 *                               wait
 *
 * In order to execute n taks , n threads need to be created making the program slow
 * IT should be better to reuse  h < n threads so that we can improve the performances
 *      1.- The thread need not to stop once completed a run -> usin a looper
 *      2.- the thread should be resuable so that in can execute many task -> looper and messageQueue object
 *      3.- many Threads can send messages of execution so that the same thread executes the task serially
 *
 * In order to achieve the above behaviour it is necessary:
 * A looper  which has a messageQueue
 * A handler which  enqueue the messages in to the message queue and also provides
 * the methods to handle those messages
 * One thread only have one Looper  which is stored in local storage
 * it can only exists one looper per thread and also one MessageQueue per thread
 * A thread can have many handlers
 * A handler enqueue tasks by using sendMessage()
 * stopSelfWithResult(startId) finishes a services if the most recent called with startId is finished()
 *
 *HandlerThread is provided by android and it has robust implementation
 *
 */
class ServiceCounter: Service() {
    private lateinit var serviceLooper: Looper
    private lateinit var serviceHandler: ServiceHandler

    private inner class ServiceHandler(looper: Looper): Handler(looper) {
        override fun handleMessage(msg: Message) {

            Log.i(TAG,"managing this message...")
            Log.i(TAG,"${msg.arg1}... ${msg.arg2}")
            var i:Int
            for(i in 1..10){
                Log.i(TAG,"$i...")
                Thread.sleep(1000)
            }

            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {

        Log.i(TAG, "Creating service...")

        val hr = HandlerThread("HandlerServiceCounter").apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(serviceLooper)
        }


    }

    /**
     * onStartCommand this  method is called when  the service is started with startService() in other component
     * two options
     * service first time started : onCreate is called, then this one
     * service already started: by another component, onCreate is not called
     * flag: START_STICKY
        If the system kills the service after onStartCommand() returns, recreate the service and call onStartCommand(),
            but do not redeliver the last intent. Instead, the system calls onStartCommand() with a null intent unless
            there are pending intents to start the service. In that case, those intents are delivered. This is suitable for
            media players (or similar services) that are not executing commands but are running indefinitely and waiting for a job.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "starting service...")
        val v1 = intent?.getIntExtra("Numero1", 0) ?: 0
        val v2 = intent?.getIntExtra("Numero2", 0) ?: 0
        Log.i(TAG, "intent values...$v1 $v2")
        val msg = serviceHandler.obtainMessage()
        msg.arg1 = startId
        msg.arg2 = v2
        serviceHandler.sendMessage(msg)




        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "destroying service...")

    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    companion object{
        val TAG = "ServiceCounterLogger"

    }
}
