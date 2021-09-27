package com.example.daggerdiapp

import android.app.Application
import android.util.Log
import com.example.daggerdiapp.modules.NetWorkModule
import com.example.daggerdiapp.modules.SubComponentsModule
import dagger.Component
import javax.inject.Scope


// Creates MyCustomScope
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class OnlyOneInstanceScope


/**
 * in order to create a subcomponent and mange the lifecycle of the sub
 * we need to :
 * 1) change the component responsibilities
 * 2) the new responsabilitie is to provide the factory class to create a subcomponent
 * 3) declare a moduble which connect component with subcomponent this module does not have code
 * 4) the previous module just specifies as a subcomponent the subcomponent
 * 5) in the subcomponent interface declare as @Subcomponent and declare an inner interface which is returns the factory class of the subcomponent itself also as a subcomponent
 * 6) declare the inject methods which inject an specific client some dependencies recursively
 * 7) on the client consumer of the component create the component  ref
 * 8) on the client consumer of the subcomponent use  the previous ref component and create the subcomponent
 * 9) call the method wich injects the subcomponent client on oncreate
 */

@OnlyOneInstanceScope
@Component(modules = [NetWorkModule::class, SubComponentsModule::class])
interface ApplicationComponent{
    fun loginComponent():LoggingComponent.Factory
}



@OnlyOneInstanceScope
class MyApplication: Application() {
    val appComponent:ApplicationComponent = DaggerApplicationComponent.create()

    init {
        Log.i(TAG, "It getting initialized...")
        Log.i(TAG, "ApplicationComponent created...")
    }
    companion object{
        const val TAG = "LOG:MyApplication"
    }
}