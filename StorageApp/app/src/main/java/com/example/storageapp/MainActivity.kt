package com.example.storageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportFragmentManager.commit{
            add<AppSpecificStorageFragment>(R.id.fragmentContainer_appSpecificStorage, AppSpecificStorageFragment.TAG)
        }

    }




}