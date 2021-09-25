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
import com.example.restclientretrofitapp.models.*
import com.example.restclientretrofitapp.services.MarsApi.MarsApi
import com.example.restclientretrofitapp.services.UsersApi.UsersApi
import kotlinx.coroutines.*
import okhttp3.ResponseBody
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

    private var user:User? = null

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
                    Log.i(TAG, "sending UPDATE request...")
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "sending UPDATE request...", Toast.LENGTH_SHORT).show()
                    this@ViewModelMainActivity.updateUser()
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "UPDATE Request sent waiting for data ", Toast.LENGTH_SHORT).show()
                }
                getString(R.string.DELETE) ->{

                }
                getString(R.string.DELETEwithID) ->{
                    Log.i(TAG, "sending DELETE request...")
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "sending DELETE request...", Toast.LENGTH_SHORT).show()
                    this@ViewModelMainActivity.deleteUser()
                    Toast.makeText(this@ViewModelMainActivity.mainActivityInstance!!.applicationContext, "DELETE Request sent waiting for data ", Toast.LENGTH_SHORT).show()

                }
            }

        }

    }

    private fun deleteUser() {
        Log.i(TAG, "DELETE request END Point  photos API gorest...")
        try {

            val call:Call<ResponseBody?> = UsersApi.retrofitUserApiService.deleteUser(user!!.id.toString(), UsersApi.user)
            call.enqueue(object:Callback<ResponseBody?>{
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    responseBody: Response<ResponseBody?>
                ) {
                    if(responseBody.isSuccessful){
                        Log.i(TAG,"The response was successful... this is the response \n\r ${responseBody.raw()}")
                        if(responseBody.body() != null ){
                            val userResponseBody = responseBody.body() as ResponseBody
                            val resultString:String = userResponseBody.toString()
                            Log.i(TAG, "Data retrieved :${resultString}")
                            this@ViewModelMainActivity._status.value = resultString
                        }else{
                            Log.i(TAG, "Delete returned null body")
                        }

                    }
                }
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.printStackTrace()}")
                }

            })




        }catch (e:Exception){
            Log.i(TAG, "There was a failure trying to make the request ${e.printStackTrace()}")
        }


    }

    private fun updateUser(){
        Log.i(TAG, "UPDATE request END Point  photos API gorest...")
        try {

            user?.apply {
                this.gender = "female"
                this.name = "mariana"
            }

            val call:Call<UserPUTResponse> = UsersApi.retrofitUserApiService.updateUser(user!!.id.toString(),user!!, UsersApi.user)
            call.enqueue(object:Callback<UserPUTResponse>{
                override fun onResponse(
                    call: Call<UserPUTResponse>,
                    putResponse: Response<UserPUTResponse>
                ) {
                    if(putResponse.isSuccessful){
                        Log.i(TAG,"The response was successful... this is the response \n\r ${putResponse.raw()}")
                        val userPUTResponse = putResponse.body() as UserPUTResponse
                        val resultString:String = userPUTResponse.toString()
                        Log.i(TAG, "Data retrieved :${resultString}")
                        this@ViewModelMainActivity._status.value = resultString
                    }
                }
                override fun onFailure(call: Call<UserPUTResponse>, t: Throwable) {
                    Log.i(TAG, "Error: ${t.printStackTrace()}")
                }

            })




        }catch (e:Exception){
            Log.i(TAG, "There was a failure trying to make the request ${e.printStackTrace()}")
        }

    }
    private fun postUser() {
        Log.i(TAG, "POST request END Point  photos API gorest...")
        try {

            /**
             * In order to make the post request  as we remmeber
             * there's an object of type RequestBody to fill the body of the request
             * and also there's an object of type ResponseBody that gives the response in some format
             *
             * If moshi can convert the kotlin object to json then this can be sent into the body of the http request
             *
             */
            user = User(null, "Mildreth", "beltranTorres@hotmail.com", "female", "active")

            val call:Call<UserPOSTResponse> = UsersApi.retrofitUserApiService.postUser(user!!, UsersApi.user)
            call.enqueue(object:Callback<UserPOSTResponse>{
                override fun onResponse(
                    call: Call<UserPOSTResponse>,
                    postResponse: Response<UserPOSTResponse>
                ) {
                    if(postResponse.isSuccessful){
                        Log.i(TAG,"The response was successful... this is the response \n\r ${postResponse.raw()}")
                        val postResponse = postResponse.body() as UserPOSTResponse
                        user?.id = postResponse.data?.id
                        val resultString:String = postResponse.toString()
                        Log.i(TAG, "Data retrieved :${resultString}")
                        this@ViewModelMainActivity._status.value = resultString
                    }
                }
                override fun onFailure(call: Call<UserPOSTResponse>, t: Throwable) {
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
            val call:Call<UserGETResponse> = UsersApi.retrofitUserApiService.getUsersResponse()
            call.enqueue(object:Callback<UserGETResponse>{
                override fun onResponse(
                    call: Call<UserGETResponse>,
                    GETResponse: Response<UserGETResponse>
                ) {
                   if(GETResponse.isSuccessful){
                       Log.i(TAG,"The response was successful... this is the response \n\r ${GETResponse.raw()}")
                       val userResponse = GETResponse.body() as UserGETResponse
                       val resultString:String = userResponse.toString()
                       Log.i(TAG, "Data retrieved :${resultString}")
                       this@ViewModelMainActivity._status.value = resultString
                   }
                }
                override fun onFailure(call: Call<UserGETResponse>, t: Throwable) {
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
            val call:Call<UserGETResponse> = UsersApi.retrofitUserApiService.getUser(id)
            call.enqueue(object:Callback<UserGETResponse>{
                override fun onResponse(
                    call: Call<UserGETResponse>,
                    GETResponse: Response<UserGETResponse>
                ) {
                    if(GETResponse.isSuccessful){
                        Log.i(TAG,"The response was successful... this is the response \n\r ${GETResponse.raw()}")
                        val userResponse = GETResponse.body() as UserGETResponse
                        val resultString:String = userResponse.toString()
                        Log.i(TAG, "Data retrieved :${resultString}")
                        this@ViewModelMainActivity._status.value = resultString
                    }
                }
                override fun onFailure(call: Call<UserGETResponse>, t: Throwable) {
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