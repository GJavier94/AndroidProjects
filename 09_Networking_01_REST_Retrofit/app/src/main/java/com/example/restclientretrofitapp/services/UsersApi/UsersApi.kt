package com.example.restclientretrofitapp.services.UsersApi


import android.util.Log
import com.example.restclientretrofitapp.models.User
import com.example.restclientretrofitapp.models.UserGETResponse
import com.example.restclientretrofitapp.models.UserPOSTResponse
import com.example.restclientretrofitapp.models.UserPUTResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


object UsersApi {

    val user:String = "d7d2d0df7131c0baf62878f7533d2f55a18fe39131b604729ff86ba2a5b10f81"
    //private val authToken = Credentials.basic(user, "")
    //private val authenticationInterceptor: AuthenticationInterceptor =
    //    AuthenticationInterceptor(authToken)

    /**
     * WE NEED TO LOOG WHAT THE RETROFIT REQUEST ARE GETTING
     */
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    private val client:OkHttpClient = OkHttpClient().newBuilder().run {
        addInterceptor(logging)
      //  addInterceptor(authenticationInterceptor)
        build()
    }


    private val moshi = Moshi.Builder().run {
        add(KotlinJsonAdapterFactory())
        build()
    }

    private val BASE_URL = "https://gorest.co.in/public/v1/"


    private val retrofit: Retrofit = Retrofit.Builder().run {
            addConverterFactory(MoshiConverterFactory.create(moshi))
            baseUrl(BASE_URL)
            client(client)
            build()
    }


    interface ClientsApiService{
        @GET("users") // we can use parameters on the URL (URI) to refine the http verb
        fun getUsersResponse():Call<UserGETResponse>

        @GET("users")
        fun getUser(@Query("id") id: Int): Call<UserGETResponse>


        @POST("users")
        fun postUser(@Body user: User, @Query("access-token") accessToken:String): Call<UserPOSTResponse>

        @PUT("users/{id}")
        fun updateUser(@Path("id") id:String, @Body user: User,@Query("access-token") accessToken:String): Call<UserPUTResponse>

        @DELETE("users/{id}")
        fun deleteUser(@Path("id") id: String, @Query("access-token") accessToken:String): Call<ResponseBody?>
    }


    val retrofitUserApiService: ClientsApiService by lazy {
            Log.i(TAG, "creating service by being called first time")
            retrofit.create(ClientsApiService::class.java)
    }

    const val TAG = "UsersApiL"

}



