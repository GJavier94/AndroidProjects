package com.example.mainactivity.BindServices

import androidx.lifecycle.ViewModel

class ViewModelClientAct : ViewModel() {
    private var numero:Int = 0
        get(){
            return field
        }
        set(value: Int) {
            field = value
        }
    fun setNumero(numero: String){
        this.numero = numero.toInt()
    }
    fun getNumero():String{
        return this.numero.toString()
    }
}
