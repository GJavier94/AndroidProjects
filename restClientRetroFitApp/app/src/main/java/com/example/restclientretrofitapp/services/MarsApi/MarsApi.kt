package com.example.restclientretrofitapp.services.MarsApi

import com.example.restclientretrofitapp.models.MarsPhoto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


//this implements singleton pattern
// initialization is made at first access
//retrofit.create() function is expensive that's why we have a singleton object accessible anywhere

// lazy initialization means it will be initialized at first usage

// we will use the factory pattern to create the retrofitService
object MarsApi {
    /**
     * WE NEED TO LOOG WHAT THE RETROFIT REQUEST ARE GETTING
     */
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client: OkHttpClient = OkHttpClient().newBuilder().run {
        addInterceptor(logging)
        build()
    }
    /**
     * Once the dependencies have been changed we can make use of moshi third party library
     */

    private val moshi = Moshi.Builder().run {
        add(KotlinJsonAdapterFactory())
        build()
    }

    /**
     * This is the BASE URI used to established communication with the rest API Service
     */
    private val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

    /**
     * In order to use the retrofit library is necessary an instance object
     * of class type Retrofit  by using the builder pattern the retrofit library implements
     * it needs:
     * the base url api service
     * the converterfactory object to convert json to string
     */

    private val retrofitForMarsAPI: Retrofit = Retrofit.Builder().run {
        addConverterFactory(MoshiConverterFactory.create(moshi))
        baseUrl(BASE_URL)
        client(client)
        build()
    }


    /**
     * This interface is the one used  to create the CRUD methods so that
     * retrofit creates code to make the http request needed
     * Like in SQLite in this case the retrofit library is in charge to
     * create a class which implements this interface and implements the method's code
     * to do the request and receive the responses
     */
    interface MarsApiService{
        //This tells retrofit that it is going to be a get request and that the end point is value = "photos"
        // retrofit  creates the URI "https://android-kotlin-fun-mars-server.appspot.com/photos"
        // let's tell the return type is an string so that the moshi converter converts the json into string
        @GET("photos")
        fun getPhotos(): Call<List<MarsPhoto>>

    }

    /**
     * This will be the newtork layer the ViewModel will use to make
     * web service request to a API REST
     * this object static class expose the service so that any other app component can use it
     */

    val retrofitService: MarsApiService by lazy {
        retrofitForMarsAPI.create(MarsApiService::class.java)
    }
}



