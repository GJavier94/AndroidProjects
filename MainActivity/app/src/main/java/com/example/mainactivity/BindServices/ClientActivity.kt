package com.example.mainactivity.BindServices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.mainactivity.R

class ClientActivity : AppCompatActivity() {
    private lateinit var textViewRandomNumber: TextView
    private lateinit var ButtonBindToService: Button
    private val viewModel:ViewModelClientAct = ViewModelClientAct()
    private var mBound: Boolean = false

    //we need to create a ref to the service and a connection object channel
    private lateinit var myService:BindLocalService

    private  val connection:ServiceConnection = object:ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BindLocalService.MyBind
            myService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client)
        ButtonBindToService = findViewById<Button>(R.id.ButtonBindToService)
        textViewRandomNumber = findViewById<TextView>(R.id.textViewRandomNumber)
        textViewRandomNumber.text = viewModel.getNumero()




    }
    fun changeNumber(view: View){
        if(mBound){
            viewModel.setNumero(myService.getRandomNumber().toString())
            textViewRandomNumber.text = viewModel.getNumero()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "calling on Start")
        val intent = Intent(this, BindLocalService::class.java)
        Log.i(TAG, "binding the $TAG  to service")
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }
    companion object{
        val TAG = "ClientActivityLogger"
    }
}