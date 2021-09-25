package com.example.restclientretrofitapp.viewmodels

import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restclientretrofitapp.MainActivity
import com.example.restclientretrofitapp.R
import com.example.restclientretrofitapp.models.MarsPhoto
import com.example.restclientretrofitapp.models.User
import com.example.restclientretrofitapp.models.UserResponse
import com.example.restclientretrofitapp.services.MarsApi.MarsApi
import com.example.restclientretrofitapp.services.UsersApi.UsersApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.lang.NullPointerException

class ViewModelMainActivity: ViewModel() {

    var mainActivityInstance: MainActivity? = null
    var adapterRetrofitOptions:MutableLiveData<ArrayAdapter<String>?> = MutableLiveData()
    var itemSelectedPosition:MutableLiveData<Int> = MutableLiveData(0)
    private var requestOptionsArray: Array<String>? = null
    var itemSelected:String? = null

    
    private var _photosList:MutableLiveData<List<MarsPhoto>> = MutableLiveData(mutableListOf())
    val photosList:LiveData<List<MarsPhoto>> = _photosList


    private var _status = MutableLiveData<String>()
    val status:LiveData<String> = _status



    fun setInstanceVariables(mainActivity: MainActivity) {
        this.mainActivityInstance = mainActivity
        requestOptionsArray = mainActivity.resources.getStringArray(R.array.httpRetroFitOptions)
        mainActivity.apply {
            Log.i(TAG, "Applying value to adapterRetrofitOptions")
            this@ViewModelMainActivity.adapterRetrofitOptions.value = ArrayAdapter(this.applicationContext, android.R.layout.simple_list_item_1, this.resources.getStringArray(
                R.array.httpRetroFitOptions))
        }

        itemSelectedPosition.observeForever { itemSelectedPosition ->
            try {
                this.itemSelected = requestOptionsArray!![itemSelectedPosition]
                Log.i(TAG, "itemSelected:${this.itemSelected}")
            } catch (e: NullPointerException) {
                Log.i(TAG, "The array was null ref ${e.printStackTrace()}")
            }
        }
    }

    fun makeRequest(view: View){
        Log.i(TAG, "makeRequests...")
        this.mainActivityInstance?.resources?.apply {
            Log.i(TAG,"you selected ${this@ViewModelMainActivity.itemSelected}")
            when(this@ViewModelMainActivity.itemSelected){
                getString(R.string.GET) ->{
                    Log.i(TAG, "sending request...")
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "sending request...", Toast.LENGTH_SHORT).show()
                    this@ViewModelMainActivity.getMarsPhotos()
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "Request sent waiting for data ", Toast.LENGTH_SHORT).show()
                }
                getString(R.string.GET_API_USER) ->{
                    Log.i(TAG, "sending request...to the web service API ")
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "sending request...", Toast.LENGTH_SHORT).show()
                    this@ViewModelMainActivity.getUsers()
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "Request sent waiting for data ", Toast.LENGTH_SHORT).show()
                }


                getString(R.string.GET_API_USERwithID) ->{
                    Log.i(TAG, "sending request...")
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "sending request...", Toast.LENGTH_SHORT).show()
                    this@ViewModelMainActivity.getUserwithID()
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "Request sent waiting for data ", Toast.LENGTH_SHORT).show()
                }
                getString(R.string.POST) ->{

                }
                getString(R.string.POST_one_USER) ->{
                    Log.i(TAG, "sending POST request...")
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "sending POST request...", Toast.LENGTH_SHORT).show()
                    this@ViewModelMainActivity.postUser()
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "POST Request sent waiting for data ", Toast.LENGTH_SHORT).show()
                }
                getString(R.string.PUT) ->{

                }
                getString(R.string.DELETE) ->{

                }
            }

        }

    }

    private fun postUser() {
        Log.i(TAG, "POST request END Point  photos API gorest...")
        try {

            val user = User(null, "javier", "javarmgar@gmail.com", "male", "active")


            val call:Call<UserResponse> = UsersApi.retrofitUserApiService.postUser(user)
            call.enqueue(object:Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){
                        Log.i(TAG,"The response was successful... this is the response \n\r ${response.raw()}")
                        val userResponse = response.body() as UserResponse
                        val resultString:String = userResponse.toString()
                        Log.i(TAG, "Data retrieved :${resultString}")
                        this@ViewModelMainActivity._status.value = resultString
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.printStackTrace()}")
                }

            })




        }catch (e:Exception){
            Log.i(TAG, "There was a failure trying to make the request ${e.printStackTrace()}")
        }

    }

    private fun getUsers() {
        Log.i(TAG, "Retrieving data from API web service USER")
        Log.i(TAG, "Thread.name ${Thread.currentThread().name}")
        try {
            val call:Call<UserResponse> = UsersApi.retrofitUserApiService.getUsersResponse()
            call.enqueue(object:Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                   if(response.isSuccessful){
                       Log.i(TAG,"The response was successful... this is the response \n\r ${response.raw()}")
                       val userResponse = response.body() as UserResponse
                       val resultString:String = userResponse.toString()
                       Log.i(TAG, "Data retrieved :${resultString}")
                       this@ViewModelMainActivity._status.value = resultString
                   }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.printStackTrace()}")
                }

            })
        }catch (e:Exception){
            Log.i(TAG, "There was a failure trying to make the request ${e.printStackTrace()}")
        }

    }

    private fun getUserwithID() {
        Log.i(TAG, "Retrieving item  from API web service endpoint Photos")
        try {
            val id = 34
            val call:Call<UserResponse> = UsersApi.retrofitUserApiService.getUser(id)
            call.enqueue(object:Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful){
                        Log.i(TAG,"The response was successful... this is the response \n\r ${response.raw()}")
                        val userResponse = response.body() as UserResponse
                        val resultString:String = userResponse.toString()
                        Log.i(TAG, "Data retrieved :${resultString}")
                        this@ViewModelMainActivity._status.value = resultString
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.printStackTrace()}")
                }

            })




        }catch (e:Exception){
            Log.i(TAG, "There was a failure trying to make the request ${e.printStackTrace()}")
        }


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
        Log.i(TAG,"The request is made in ${Thread.currentThread().name}")
        try {
            val call:Call<List<MarsPhoto>> = MarsApi.retrofitService.getPhotos()
            Log.i(TAG,"${Thread.currentThread().name} the ${MarsApi.retrofitService.getPhotos()} has been invoked...")

            call.enqueue(object:Callback<List<MarsPhoto>>{
                override fun onResponse(
                    call: Call<List<MarsPhoto>>,
                    response: Response<List<MarsPhoto>>
                ) {
                    Log.i(TAG, "The response has been received ${response.raw()}")
                    if(response.isSuccessful){
                        val marsPhotoList:List<MarsPhoto>? = response.body()
                        _photosList.value = marsPhotoList
                        val resultString:String? = photosList.value?.run{ map { marsPhoto -> marsPhoto.toString() }.reduce { acc, s -> acc + "\n\r" + s } }
                        Log.i(TAG, "Data retrieved :${resultString}")
                        this@ViewModelMainActivity._status.value = resultString
                    }else{
                        // check for the error response 404 , 500 and so on
                        Log.i(TAG, "${response.raw()}")
                    }
                }

                override fun onFailure(call: Call<List<MarsPhoto>>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.printStackTrace()}")
                }

            })




        }catch (e:Exception){
            Log.i(TAG, "There was a failure trying to make the request ${e.printStackTrace()}")
        }

    }

    fun clearInstanceVariables() {
        this.mainActivityInstance = null
    }

    companion object{
        const val TAG = "ViewModelMainActivityL"
    }
}