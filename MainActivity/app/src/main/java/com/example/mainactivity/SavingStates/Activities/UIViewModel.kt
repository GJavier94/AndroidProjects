package com.example.mainactivity.SavingStates.Activities

import android.util.Log
import androidx.lifecycle.ViewModel

class UIViewModel: ViewModel() {
    init {
        Log.i(TAG, "Initializing UIViewModel")
    }

    var  counterTextView2 = 0;
    var  counterTextView3 = 0;

    fun increaseCounting(){
        this.counterTextView2++
        this.counterTextView3++
    }
    fun resetCounting(){
        this.counterTextView2 = 0
        this.counterTextView3 = 0
    }

    override fun onCleared() {
        super.onCleared()
        // here it should realeased some components
    }

    companion object{
        val TAG = "UIViewModelLogger"
    }
}
