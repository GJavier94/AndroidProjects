package com.example.daggerdiapp

import android.app.Application
import android.util.Log
import com.example.daggerdiapp.models.NetWorkModule
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton


// Creates MyCustomScope
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class MyCustomScope

/**
 * For classes which are not instantiated by the user
 * like activities
 * they do not have constructor so how can we inject dependencies?
 * by injecting attributes for example -> activity get injected a viewModel as a field
 *
 * if we inject fields => the component interface changes
 * => instead of fun <nameFun>():<dataReturnType>()
 * => we have fun <nameFun>( client: <ClassClient>): all the fields with the annotation @inject that are not private are injected to the client
 * the clients needs to get a reference of the component and call inject
 * so the client injects itself
 *
 * activity
 * (this.applicattionContext as <nameClassInjector>).<nameInterface>.inject(<client>)
 * (this.applicattionContext as MyApplication).appComponent.inject(this)
 */
@MyCustomScope
@Component(modules = [NetWorkModule::class])
interface ApplicationComponent{
    fun inject(activity: MainActivity)
}

@MyCustomScope
class MyApplication: Application() {
    val appComponent:ApplicationComponent = DaggerApplicationComponent.create()

    init {
        Log.i(TAG, "It getting initialized...")
    }
    companion object{
        const val TAG = "LOG:MyApplication"
    }
}