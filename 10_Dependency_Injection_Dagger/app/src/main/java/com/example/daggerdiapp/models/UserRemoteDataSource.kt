package com.example.daggerdiapp.models

import android.util.Log
import com.example.daggerdiapp.restservices.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
class UserRemoteDataSource @Inject constructor(
    val exampleRestService: ApiService
){
    val data = "Hi im UserRemoteDataSource"
    init {
        Log.i(TAG, data)
        this.exampleRestService.getUsersResponse().enqueue(object:
            retrofit2.Callback<UserGETResponse> {
            override fun onResponse(
                call: Call<UserGETResponse>,
                response: Response<UserGETResponse>
            ) {
                if(response.isSuccessful){
                    val result = (response.body() as UserGETResponse).toString()
                    Log.i(TAG,result )
                }
            }

            override fun onFailure(call: Call<UserGETResponse>, t: Throwable) {
                Log.i(TAG,"context.getString(R.string.FAILURE)" )
            }
        })
    }
    companion object{
        const val TAG = "LOG:UseRemoteDSourceL"
    }
}