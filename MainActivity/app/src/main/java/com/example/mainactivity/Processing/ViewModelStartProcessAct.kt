package com.example.mainactivity.Processing

import androidx.lifecycle.ViewModel

class ViewModelStartProcessAct : ViewModel() {

    var v1 = 0
        get() = field
        set(value1) {
            println("changing v1 to $v1")
            field = value1
        }
    var v2 = 0
        get() = field
        set(value1) {
            println("changing v2 to $v2")
            field = value1
        }
}