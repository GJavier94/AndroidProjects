package com.example.menusapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelMainActivity : ViewModel() {

    internal var isActionModeOn: MutableLiveData<Boolean> = MutableLiveData(false)

}