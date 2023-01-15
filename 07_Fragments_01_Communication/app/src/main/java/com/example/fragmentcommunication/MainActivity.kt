package com.example.fragmentcommunication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fragmentcommunication.ViewModelCom.OneActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonFragmentWithAPI: Button
    private lateinit var buttonFragmentComViewModel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonFragmentComViewModel = findViewById<Button>(R.id.buttonFragmentComViewModel)

        buttonFragmentComViewModel.setOnClickListener {
            Intent(this, OneActivity::class.java).apply {
                startActivity(this)
            }
        }

        buttonFragmentWithAPI = findViewById<Button>(R.id.buttonFragmentWithAPI)

        buttonFragmentWithAPI.setOnClickListener {
            Intent(this, SecondActivity::class.java).apply {
                startActivity(this)
            }
        }

    }
}