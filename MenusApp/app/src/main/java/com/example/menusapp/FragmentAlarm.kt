package com.example.menusapp

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView


import com.example.menusapp.ViewModels.ViewModelFragmentAlarm
import com.example.menusapp.ViewModels.ViewModelMainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton



class FragmentAlarm : Fragment() {
    private val viewModel:ViewModelFragmentAlarm by viewModels()
    private val viewModelActivity: ViewModelMainActivity by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonAddAlarm: FloatingActionButton

    /**
     * There are also other types of menus called
     * CONTEXTUAL MENU
     * This  menu is offers actions that affects the specific item  or context frame from a UI
     * There are two types of contextual Menus
     * 1.-floating contextual menu
     * 2.- contextual action mode
     *
     * 1.- Floating contextual menu
     * When the user does a long click over an Item this fires a floating menu with a set of actions to
     * be applied over that specific item
     * In order to implement this specific menu the following steps are required:
     *
     * 1.- Create the menu resource xml file on the resource folder res/menu
     * 2.- Register a view into the contextMenu by calling registerForContextMenu(view)
     *          you can pass it a View or a set of Views like a ListView or GridView
     * 3.- Activities and Fragments have onCreateContextMenu
     * 4.-  view component receives a long click event the onCreateContextMenu is called and the floating menu is inflated
     * 5.- onContext
     *
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //we register the variable of the FragmentViewModel to change
        // when the variable
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alarm, container, false)
        recyclerView = view.findViewById(R.id.framgment_alarm_RecyclerView_alarm)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonAddAlarm = view.findViewById(R.id.button_add_alarm)
        //we register the view for context menu
        this.registerForContextMenu(buttonAddAlarm)


        /*
        When the view is created we can set the  recyclerView
        **/


        if(viewModel.createAdapter(this.activity)){
            Log.i(TAG, "Adapter is loaded")
            recyclerView.adapter = viewModel.alarmAdapter

        }else{
            Log.i(TAG, "Adapter couldn't be loaded")
            Toast.makeText(this.context, "Adapter couldn't be loaded", Toast.LENGTH_SHORT).show()
        }

        //best place to start observing actionMode Variable
        viewModel.isActionModeOn.observe(this.viewLifecycleOwner, Observer {
            isActionModeOn ->
            if(isActionModeOn){
                viewModel.actionMode = this.activity?.startActionMode(viewModel.callBackActionMode)
            }else{
                Log.i(TAG, "Getting out from the action mode...")
            }
        } )
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

    }
    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo? // This provides info of the item which was selected
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater:MenuInflater? = this.activity?.menuInflater

        inflater?.inflate(R.menu.fragment_alarm_context_menu, menu)




    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo
        Log.i(TAG, "$info")

        return when(item.itemId){
            R.id.context_menu_fragment_alarm_item_disable -> {
                Toast.makeText(this.context,"Disabling adding alarm...", Toast.LENGTH_SHORT).show()
                //here we should disable the option of adding more alarms to the fragment
                true
            }
          else -> {
              super.onContextItemSelected(item)
          }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }
    companion object {
        const val TAG = "FragmentAlarmLogger"
        const val RECYCLER_VIEW_ID = "fragment_alarm_context_menu"
    }
}