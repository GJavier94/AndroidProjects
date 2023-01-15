package com.example.daggerdiapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.daggerdiapp.ApplicationGraph
import com.example.daggerdiapp.DaggerApplicationGraph
import com.example.daggerdiapp.repositories.UserRepository


class ViewModelMainActivity: ViewModel() {
    val text = "Hola"
    //Dagger creates a new instance of UserRepository every time it's requested.
    val applicationGraph:ApplicationGraph = DaggerApplicationGraph.create()
    val userRepository: UserRepository = applicationGraph.repository()

    /**
     * In order to have the same object instance when calling a method from the
     * dagger component we will need to define scope annotations
     *
     * Limits the lifetime of an object to the lifetime of its component
     *
     *
     * e.g we would like to reuse the same userRepository instance
     *
     * scope annotation -> @Singleton
     * we can define our custom Scope annotations
     * new annotation -> new class
     *
     * @Scope @MustBeDocumented @Retention(value = AnnotationRetention.RUNTIME)
     * annotation class MyCustomScope -> annotation class <nameAnnotation>
     * Then use @MyCustomScope -> @<nameAnnotation>
     * so that every time we call repository we get the same instance
     */

    val userRepository1: UserRepository = applicationGraph.repository()
    val userRepository2: UserRepository = applicationGraph.repository()

    init {
        Log.i(TAG, "Initializing  ViewModelMainActivity...")
        checkInstanceRepos()
    }

    private fun checkInstanceRepos() {
        if( this.userRepository1 == this.userRepository2){
            Log.i(TAG, "The repos are the same instance")
            this.userRepository.localDataSource.data
            this.userRepository.remoteDataSource.data
        }else{
            Log.i(TAG, "The instances are different")
        }
    }

    companion object {
        const val TAG = "LOG:ViewMMActivityL"
    }
}
