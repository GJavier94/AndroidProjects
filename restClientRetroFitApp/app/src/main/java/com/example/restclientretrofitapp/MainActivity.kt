package com.example.restclientretrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.example.restclientretrofitapp.databinding.ActivityMainBinding
import com.example.restclientretrofitapp.viewmodels.ViewModelMainActivity

class MainActivity : AppCompatActivity() {
    val viewModel: ViewModelMainActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
    }
}