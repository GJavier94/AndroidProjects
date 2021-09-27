package com.example.daggerdiapp

import com.example.daggerdiapp.models.NetWorkModule
import com.example.daggerdiapp.repositories.UserRepository
import dagger.Component
import javax.inject.Singleton

//@Singleton
// we can use custom scope annotations

@MyCustomScope
@Component(modules = [NetWorkModule::class])
interface ApplicationGraph {
    fun repository(): UserRepository
}