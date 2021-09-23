package com.example.restclientretrofitapp.services

import com.squareup.moshi.Moshi



/**
 * This is the BASE URI used to established communication with the rest API Service
 */
val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

/**
 * In order to use the retrofit library is necessary an instance object
 * of class type Retrofit  by using the builder pattern the retrofit library implements
 * it needs:
 * the base url api service
 * the converterfactory object to convert json to string
 */

val retrofit:Retrofit = Retrofit.Builder().run {
                            addConverterFactory(ScalarsConverterFactory.create())
                            baseUrl(BASE_URL)
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
    suspend fun getPhotos():String

}

/**
 * This will be the newtork layer the ViewModel will use to make
 * web service request to a API REST
 * this object static class expose the service so that any other app component can use it
 */

//this implements singleton pattern
// initialization is made at first access
//retrofit.create() function is expensive that's why we have a singleton object accessible anywhere

// lazy initialization means it will be initialized at first usage

// we will use the factory pattern to create the retrofitService
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}



