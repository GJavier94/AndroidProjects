package com.example.daggerdiapp.models

import com.example.daggerdiapp.MyCustomScope
import com.example.daggerdiapp.restservices.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Inject

// @Module informs Dagger that this class is a Dagger Module

@Module
class NetWorkModule {
    val baseUrl = "https://gorest.co.in/public/v1/"

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().run {
            add(KotlinJsonAdapterFactory())
            build()
        }
    }



    //@Provides annotations tells dagger how to create an instance of the type this functions returns
    @MyCustomScope
    @Provides
    fun provideExampleRetrofitService(
        moshi:Moshi
    ): ApiService {
        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            addConverterFactory(MoshiConverterFactory.create(moshi))
            build()
        }.create(ApiService::class.java)
    }

}
