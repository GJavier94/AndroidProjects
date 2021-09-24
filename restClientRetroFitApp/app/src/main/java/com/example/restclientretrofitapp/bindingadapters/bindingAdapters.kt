/*
package com.example.restclientretrofitapp.bindingadapters

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.AdapterViewBindingAdapter

fun

@BindingAdapter("android:onItemSelected", "android:onNothingSelected", requireAll = false)
fun setListener(view:View, selected: AdapterViewBindingAdapter.OnItemSelected,
                nothingSelected: AdapterViewBindingAdapter.OnNothingSelected, ) {
    val TAG = "setListenerL"

    val listener = object:AdapterView.OnItemSelectedListener{

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            Log.i(TAG, "$p0: AdapterView<*>?, $p1: View?, $p2: Int, $p3: Long")
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            Log.i(TAG, "here we don't do anything ")
        }
    }
}*/
