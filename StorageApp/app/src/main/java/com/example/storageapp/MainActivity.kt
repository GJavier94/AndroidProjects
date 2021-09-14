package com.example.storageapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.storageapp.Adapters.PageAdapter

class MainActivity : AppCompatActivity() {


    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById<ViewPager2>(R.id.viewPager)

        viewPager.adapter = PageAdapter(this, NUM_PAGES)

    }

    companion object{
        const val TAG = "MainActivityL"
        const val NUM_PAGES:Int = 2
    }




}