package com.example.fragmentcommunication.ViewModelCom.ViewModelsOneActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelFragmentSibling1: ViewModel() {
    var counterSibling1toChild : MutableLiveData<Int> = MutableLiveData<Int>(0)
}