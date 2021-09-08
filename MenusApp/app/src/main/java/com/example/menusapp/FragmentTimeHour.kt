package com.example.menusapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentTimeHour : Fragment() {


    private lateinit var buttonShowSettings: Button

    var showSettings:Boolean = false

    internal fun showSettingsEnable(){
        this.showSettings = true
    }
    internal fun showSettingsDisable(){
        this.showSettings = false
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i(TAG, "onCreateOptionsMenu")

        inflater.inflate(R.menu.fragment_alarm_menu, menu)

    }


    /**
     * This is called after the menu is created and just before it is displayed
     * it helps to show the menu in custom way
     * by hiding some items and
     *
     * in fragments
     * show(),hide()
     * create(), destroy() ----> it doesn't matter how but once the fragments  gets visible the methods are called
     *
     */
    override fun onPrepareOptionsMenu(menu: Menu) {
        val item = menu.findItem(FragmentAlarm.ID_ITEM_PROGRAMMATICALLY)
        if(item == null ){
            menu.add(0 , FragmentAlarm.ID_ITEM_PROGRAMMATICALLY, 0 , "Item programatically" )
        }
        menu.findItem(R.id.option_settings).isVisible = this.showSettings
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
        buttonShowSettings = view.findViewById<Button>(R.id.button_show_settings)
        buttonShowSettings.setOnClickListener {
            this.showSettings = !this.showSettings
        }
    }

    companion object {
        const val TAG = "FragmentTimeHour"
    }
}