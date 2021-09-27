package com.example.daggerdiapp

import dagger.Subcomponent
import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@Retention(value = RUNTIME)
annotation class ActivityScope


@ActivityScope
@Subcomponent
interface LoggingComponent {
    @Subcomponent.Factory
    interface Factory{
        fun create():LoggingComponent
    }

    fun inject(mainActivity:MainActivity)

}
