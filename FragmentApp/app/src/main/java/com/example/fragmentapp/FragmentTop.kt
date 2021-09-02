package com.example.fragmentapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FragmentTop(val data:DataFragmentTop) : Fragment() {

    private lateinit var dataTextViewTopFragment: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"onCreate")


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i(TAG,"onCreateView")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top, container, false)
        dataTextViewTopFragment = view.findViewById<TextView>(R.id.dataTextViewTopFragment)
        dataTextViewTopFragment.text = data.toString()
        return view
    }
    companion object {
        const val TAG = "FragmentTop"
    }

}