package com.example.daggerdiapp.modules

import android.util.Log
import com.example.daggerdiapp.OnlyOneInstanceScope
import com.example.daggerdiapp.restservices.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// @Module informs Dagger that this class is a Dagger Module

@Module
class NetWorkModule {
    private val baseUrl = "https://gorest.co.in/public/v1/"

    @Provides
    fun provideMoshi(): Moshi {
        Log.i(TAG, "provideMoshi: someone needs moshi")
        return Moshi.Builder().run {
            add(KotlinJsonAdapterFactory())
            build()
        }
    }



    //@Provides annotations tells dagger how to create an instance of the type this functions returns
    @OnlyOneInstanceScope
    @Provides
    fun provideExampleRetrofitService(
        moshi:Moshi
    ): ApiService {
        Log.i(TAG, "provideExampleRetrofitService: someone needs ApiService")
        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            build()
        }.create(ApiService::class.java)
    }

    companion object{
        const val TAG = "LOG:NetworkModule"
    }
}
