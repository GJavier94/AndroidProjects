package com.example.contentProviderClient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.contentProviderClient.activities.DeleteActivity
import com.example.contentProviderClient.activities.InsertActivity
import com.example.contentProviderClient.activities.RetrieveActivity
import com.example.contentProviderClient.activities.UpdateActivity

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
        buttonInsert.setOnClickListener {
            Intent(this, InsertActivity::class.java).also {
                startActivity(it)
            }
        }
        buttonUpdate.setOnClickListener {
            Intent(this, UpdateActivity::class.java).also {
                startActivity(it)
            }
        }
        buttonDelete.setOnClickListener {
            Intent(this, DeleteActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}