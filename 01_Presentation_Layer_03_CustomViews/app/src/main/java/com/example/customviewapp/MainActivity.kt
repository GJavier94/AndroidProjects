package com.example.customviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import com.example.customviewapp.CustomViews.PieChart

class MainActivity : AppCompatActivity() {
    private lateinit  var toggleButton: ToggleButton
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pieChart = findViewById(R.id.pieChart)
        editText = findViewById(R.id.EditText)
        button = findViewById(R.id.Button)
        toggleButton = findViewById(R.id.ToggleButton)

        button.setOnClickListener {
            if(pieChart.isShowText()){
                pieChart.setText(editText.text.toString())
            }
        }
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            pieChart.setShowText(isChecked)
            toggleButton.textOn = "hide text"
        }
        Log.i(TAG, "onCreate")
    }

    override fun onStart() {
        Log.i(TAG, "onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.i(TAG, "onResume")
        super.onResume()
    }
    companion object {
        const val TAG = "LOG:MainActivity"
    }
}