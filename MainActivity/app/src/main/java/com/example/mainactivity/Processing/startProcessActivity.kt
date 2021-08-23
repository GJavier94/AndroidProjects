package com.example.mainactivity.Processing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mainactivity.R

class startProcessActivity : AppCompatActivity() {
    private lateinit var buttonProcess2: Button
    private lateinit var buttonProcess1: Button
    private lateinit var viewModel: ViewModelStartProcessAct

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_process)
        viewModel = ViewModelProvider(this).get(ViewModelStartProcessAct::class.java)



        buttonProcess1 = findViewById<Button>(R.id.buttonProcess1)
        buttonProcess2 = findViewById<Button>(R.id.buttonProcess2)
        var textViewN1 = findViewById<EditText>(R.id.textViewN1)
        var textViewN2 = findViewById<EditText>(R.id.textViewN2)



        buttonProcess1.setOnClickListener {
            val intent = Intent(this, ServiceCounter::class.java).apply {
                viewModel.v1 = textViewN1.text.toString().toInt()
                viewModel.v2 = textViewN2.text.toString().toInt()

                putExtra("Numero1", viewModel.v1 )
                putExtra("Numero1", viewModel.v2 )
            }
            Log.i(TAG, "Starting service with values ${viewModel.v1} ${viewModel.v2}")
            this.startService(intent)
        }
        buttonProcess2.setOnClickListener {
            val intent = Intent(this, ServiceCounter::class.java).apply {
                viewModel.v1 = textViewN1.text.toString().toInt()
                viewModel.v2 = textViewN2.text.toString().toInt()
                putExtra("numero1", viewModel.v1 )
                putExtra("numero1", viewModel.v2 )
            }
            Log.i(TAG, "Starting service with values ${viewModel.v1} ${viewModel.v2}")
            this.startService(intent)
        }
    }
    companion object{
        val TAG = "startProcessActivity"
    }
}






