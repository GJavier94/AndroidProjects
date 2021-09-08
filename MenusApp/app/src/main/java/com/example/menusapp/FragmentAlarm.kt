package com.example.menusapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView


import com.example.menusapp.ViewModels.ViewModelFragmentAlarm
import com.example.menusapp.ViewModels.ViewModelMainActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton



class FragmentAlarm : Fragment() {
    private lateinit var buttonDeleteSelectionAlarm: FloatingActionButton
    private val viewModelFragmentAlarm: ViewModelFragmentAlarm by viewModels()
    private val viewModelActivity: ViewModelMainActivity by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonAddAlarm: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        setHasOptionsMenu(true)

        viewModelFragmentAlarm.contextFragment = this.context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_alarm, container, false)
        recyclerView = view.findViewById(R.id.framgment_alarm_RecyclerView_alarm)
        return view
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        buttonAddAlarm = view.findViewById(R.id.button_add_alarm)
        buttonDeleteSelectionAlarm = view.findViewById<FloatingActionButton>(R.id.button_delete_selection_alarm)

        //we register the view for context menu
        this.registerForContextMenu(buttonAddAlarm)


        /*
        When the view is created we can set the  recyclerView
        **/


        if(viewModelFragmentAlarm.createAdapter(this.activity)){
            Log.i(TAG, "Adapter is loaded")
            recyclerView.adapter = viewModelFragmentAlarm.alarmAdapter

        }else{
            Log.i(TAG, "Adapter couldn't be loaded")
            Toast.makeText(this.context, "Adapter couldn't be loaded", Toast.LENGTH_SHORT).show()
        }

        //best place to start observing actionMode Variable
        viewModelFragmentAlarm.isActionModeOn.observe(this.viewLifecycleOwner, Observer {
                isActionModeOn ->
            viewModelActivity.isActionModeOn.value = isActionModeOn
            if(isActionModeOn){
                buttonAddAlarm.visibility = View.INVISIBLE
                buttonDeleteSelectionAlarm.visibility = View.VISIBLE

                Log.i(TAG, "The ActionMode is on")
                viewModelFragmentAlarm.actionMode = this.activity?.startActionMode(viewModelFragmentAlarm.callBackActionMode)
            }else{
                buttonAddAlarm.visibility = View.VISIBLE
                buttonDeleteSelectionAlarm.visibility = View.INVISIBLE
                Log.i(TAG, "The ActionMode is off")
            }
        } )

        buttonDeleteSelectionAlarm.setOnClickListener{
            Toast.makeText(this.context, "Deleting selected items...", Toast.LENGTH_SHORT).show()
            if( viewModelFragmentAlarm.deleteSelectedAlarms()  ){
                Toast.makeText(this.context, "Items selected deleted...", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this.context, "Couldn't delete items...", Toast.LENGTH_SHORT).show()
            }

        }


    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i(TAG, "onViewStateRestored")

    }




    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i(TAG, "onCreateOptionsMenu")

        inflater.inflate(R.menu.fragment_alarm_menu, menu)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        Log.i(TAG, "onPrepareOptionsMenu")

        val item = menu.findItem(ID_ITEM_PROGRAMMATICALLY)
        if(item == null ){
            menu.add(0 , ID_ITEM_PROGRAMMATICALLY, 0 , "Item programatically" )
        }
    }

    //this method is called if the parent( act or fragment) wasn't able to handle the item
    // if you are not able to handle it let others child fragments handle it

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.option_add_alarm ->{
                Toast.makeText(this.context, "Opening a fragment to add alarm", Toast.LENGTH_SHORT).show()
                true
            }
            else ->{
                super.onOptionsItemSelected(item) //returns false
            }
        }
    }


    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo? // This provides info of the item which was selected
    ) {
        Log.i(TAG, "onCreateContextMenu")
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
        Log.i(TAG, "onSaveInstanceState")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
        viewModelFragmentAlarm.contextFragment = null
    }
    companion object {
        const val TAG = "FragmentAlarmLogger"
        const val RECYCLER_VIEW_ID = "fragment_alarm_context_menu"
        const val ID_ITEM_PROGRAMMATICALLY = 5550
    }
}