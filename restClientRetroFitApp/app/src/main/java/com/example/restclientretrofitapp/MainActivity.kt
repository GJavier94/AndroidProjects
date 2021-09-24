package com.example.restclientretrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.restclientretrofitapp.databinding.ActivityMainBinding
import com.example.restclientretrofitapp.viewmodels.ViewModelMainActivity

class MainActivity : AppCompatActivity() {
    val viewModel: ViewModelMainActivity by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.setInstanceVariables(this)


        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this // without adding this variable the data binding class will not know which is the life cycle owner of the view model
                                     // then the variables living the will not know when the other ones are active
        /**
         * This is just to test that status changes when receiving the get data from the api web service
         */
        viewModel.status.observe(this, Observer {
            Log.i(TAG, "The  status variable has changed...")
        })

        viewModel.itemSelectedPosition.observe(this, Observer {
            position ->
            Log.i(TAG, "Position changed to $position")
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearInstanceVariables()

    }
    companion object{
        const val TAG = "MainActivityL"
    }
}