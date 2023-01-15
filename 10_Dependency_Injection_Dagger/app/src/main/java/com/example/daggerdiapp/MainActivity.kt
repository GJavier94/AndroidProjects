package com.example.daggerdiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.daggerdiapp.viewmodels.LoginViewModel
import com.example.daggerdiapp.viewmodels.ViewModelMainActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var loginViewModel: LoginViewModel

    private val viewModel: ViewModelMainActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        (this.application as MyApplication).appComponent.inject(this)

        Log.i(TAG, loginViewModel.text)
        Log.i(TAG, viewModel.text)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
    companion object{
        const val TAG = "LOG:MainActivityL"
    }
}