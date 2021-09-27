package com.example.daggerdiapp

import com.example.daggerdiapp.modules.NetWorkModule
import com.example.daggerdiapp.repositories.UserRepository
import dagger.Component

//@Singleton
// we can use custom scope annotations

@OnlyOneInstanceScope
@Component(modules = [NetWorkModule::class])
interface ApplicationGraph {
    fun repository(): UserRepository
}