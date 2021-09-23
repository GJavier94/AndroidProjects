package com.example.restclientretrofitapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restclientretrofitapp.services.MarsApi
import kotlinx.coroutines.launch
import java.lang.Exception

class ViewModelMainActivity: ViewModel() {


    private var _status = MutableLiveData<String>()
    val status:LiveData<String> = _status
    init {
        //We would like that when the app and the activity is opened we
        // retrieve data from the back end service
        // and it will be done only at first creation ViewModel or when destroyed an recreated
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        /**
         * We need to make the API network request asynchronously so that we will need to use
         * coroutines => we need an scope => viewModelScope so that when cleared the scope is destroyed and the
         * coroutines are cancelled => it makes sense there's no viewModel to receive the response so we didn't care about request-response anymore
         * This implies that the api service method request needs to be run in a coroutine => is suspend
         */
        Log.i(TAG, "Retrieving data from API web service")

        /**
         * While making a request we wait for the response
         * many things can happen and we can receive nothing from the api
         * or the timeout can expire
         * so an exception is throw
         */
        viewModelScope.launch {
            try {
                _status.value = MarsApi.retrofitService.getPhotos()
                Log.i(TAG, "Retrieving data ${status.value}")

            }catch (e:Exception){
                Log.i(TAG, "There was a failure trying to make the request ${e.printStackTrace()}")
            }
        }

    }
    companion object{
        const val TAG = "ViewModelMainActivityL"
    }
}