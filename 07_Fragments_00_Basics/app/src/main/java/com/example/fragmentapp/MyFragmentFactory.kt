package com.example.fragmentapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class MyFragmentFactory(val dataFragmentTop: DataFragmentTop) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(loadFragmentClass(classLoader, className)){
            FragmentTop::class.java ->{
                FragmentTop(dataFragmentTop)
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }

    }
}
