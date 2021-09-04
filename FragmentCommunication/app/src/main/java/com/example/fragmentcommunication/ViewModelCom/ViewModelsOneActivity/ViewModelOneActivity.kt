package com.example.fragmentcommunication.ViewModelCom.ViewModelsOneActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class ViewModelOneActivity: ViewModel() {


    var counterSibling2toActivity : MutableLiveData<Int> = MutableLiveData<Int>(0)
    var counterSibling1toSibling2 :MutableLiveData<Int> = MutableLiveData<Int>(0)

    var observer: Observer<Int> = Observer<Int> {
        Log.i(TAG, "value has changed $it")
    }



    companion object{
        const val TAG = "ViewModelOneActivityLog"
    }

}