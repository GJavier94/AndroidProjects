package com.example.mainactivity.SavingStates.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.mainactivity.R

class UIActivity : AppCompatActivity() {
    private lateinit var textViewUI2: TextView
    private lateinit var textViewUI3: TextView
    private lateinit var viewModel: UIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uiactivity)
        viewModel = ViewModelProvider(this).get(UIViewModel::class.java)
        textViewUI2 = findViewById<TextView>(R.id.textViewUI2)
        textViewUI3 = findViewById<TextView>(R.id.textViewUI3)

        textViewUI2.text = viewModel.counterTextView2.toString()
        textViewUI3.text = viewModel.counterTextView3.toString()

        findViewById<Button>(R.id.buttonIncreaseCounting).setOnClickListener{
            viewModel.increaseCounting()
            textViewUI2.text = viewModel.counterTextView2.toString()
            textViewUI3.text = viewModel.counterTextView3.toString()
        }
        findViewById<Button>(R.id.buttonResetNumbers).setOnClickListener{
            viewModel.resetCounting()
            textViewUI2.text = viewModel.counterTextView2.toString()
            textViewUI3.text = viewModel.counterTextView3.toString()
        }
    }




}