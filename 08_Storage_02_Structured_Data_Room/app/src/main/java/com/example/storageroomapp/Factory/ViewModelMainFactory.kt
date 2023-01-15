package com.example.storageroomapp.Factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storageroomapp.ViewModels.ViewModelMainActivity

class ViewModelMainFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelMainActivity::class.java)) {
            return ViewModelMainActivity(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}