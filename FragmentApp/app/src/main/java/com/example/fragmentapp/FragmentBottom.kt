package com.example.fragmentapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class FragmentBottom : Fragment(R.layout.fragment_bottom) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"onCreate")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i(TAG,"onCreateView")
        return inflater.inflate(R.layout.fragment_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG,"onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        val entre:String? = requireArguments().getString("key") ?:"unknown"
        Log.i(TAG, "$entre")
    }
    companion object{
        const val TAG = "FragmentBottom"
    }
}