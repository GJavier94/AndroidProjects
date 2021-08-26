package com.example.contentproviderclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.contentproviderclient.activities.RetrieveActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttonUpdate: Button
    private lateinit var buttonDelete: Button
    private lateinit var  buttonInsert: Button
    private lateinit var  buttonRetrieve: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonRetrieve = findViewById<Button>(R.id.buttonRetrieve)
        buttonInsert = findViewById<Button>(R.id.buttonInsert)
        buttonDelete = findViewById<Button>(R.id.buttonDelete)
        buttonUpdate = findViewById<Button>(R.id.buttonUpdate)
        initListeners()

    }

    private fun initListeners() {
        buttonRetrieve.setOnClickListener {
            Intent(this, RetrieveActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}