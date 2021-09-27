package com.example.daggerdiapp.modules

import android.util.Log
import com.example.daggerdiapp.LoggingComponent
import dagger.Module


@Module(subcomponents = [LoggingComponent::class])
class SubComponentsModule {
    init {
        Log.i(TAG,"@Module(subcomponents = [LoggingComponent::class])" )
    }
    companion object{
        const val TAG ="LOG:SubComponentsModule"
    }
}
