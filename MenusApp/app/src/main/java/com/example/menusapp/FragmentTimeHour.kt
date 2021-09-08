package com.example.menusapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentTimeHour : Fragment() {



    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(FragmentAlarm.ID_ITEM_PROGRAMMATICALLY)
        if(item == null ){
            menu.add(0 , FragmentAlarm.ID_ITEM_PROGRAMMATICALLY, 0 , "Item programatically" )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_time_hour, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val TAG = "FragmentTimeHour"
    }
}