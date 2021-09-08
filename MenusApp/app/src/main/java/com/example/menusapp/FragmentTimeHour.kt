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
    var showOptionTimer = true

    internal fun showSettingsEnable(){
        this.showSettings = true
    }
    internal fun showSettingsDisable(){
        this.showSettings = false
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
        Log.i(TAG, "onPrepareOptionsMenu")
        val item = menu.findItem(FragmentAlarm.ID_ITEM_PROGRAMMATICALLY)
        if(item == null ){
            menu.add(0 , FragmentAlarm.ID_ITEM_PROGRAMMATICALLY, 0 , "Item programatically" )
        }
        menu.findItem(R.id.option_settings).isVisible = this.showSettings
        menu.findItem(R.id.option_timer).isVisible = this.showOptionTimer
    }

    /**
     * By calling invalidateOptionsMenu() on the host activity the callbacks methods are called
     * so that it recreates the menu in case
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i(TAG, "onCreateView")
        return inflater.inflate(R.layout.fragment_time_hour, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        buttonShowSettings = view.findViewById<Button>(R.id.button_show_settings)
        buttonShowSettings.setOnClickListener {
            this.showSettings = !this.showSettings
            /**
             * This works because the item  it is hidden in actions menu
             * but what if the action is not hidden there like
             * the other timer item in the menu item
             */
            this.showOptionTimer = !this.showOptionTimer
            requireActivity().invalidateOptionsMenu()


        }
    }

    companion object {
        const val TAG = "FragmentTimeHour"
    }
}