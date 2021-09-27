package com.example.daggerdiapp.restservices

import com.example.daggerdiapp.models.UserGETResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users") // we can use parameters on the URL (URI) to refine the http verb
    fun getUsersResponse(): Call<UserGETResponse>
}

