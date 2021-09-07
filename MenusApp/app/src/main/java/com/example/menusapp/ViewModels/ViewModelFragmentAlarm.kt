package com.example.menusapp.ViewModels

import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.menusapp.Adapters.AlarmAdapter
import com.example.menusapp.Models.AlarmItem
import com.example.menusapp.R

class ViewModelFragmentAlarm: ViewModel() {

    internal lateinit var alarmAdapter:AlarmAdapter

    internal  var isActionModeOn: MutableLiveData<Boolean> = MutableLiveData(false)
    internal var actionMode:ActionMode? = null

    private var dataSource:MutableLiveData<ArrayList<AlarmItem>> = MutableLiveData<ArrayList<AlarmItem>>(ArrayList())

    internal var callBackActionMode: ActionMode.Callback = object:ActionMode.Callback{

        override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
            val inflater:MenuInflater? = actionMode?.menuInflater
            inflater?.inflate(R.menu.activity_main_action_mode, menu)
            Log.i(TAG,"onCreateActionMode...Start observing")
            this@ViewModelFragmentAlarm.alarmAdapter.numItemsSelected.observeForever {
                Observer<Int> {
                    numItemsSelected ->
                    Log.i(TAG,"onCreateActionMode... numItemsSelected = $numItemsSelected")
                    menu?.findItem(R.id.action_bar_itemsSelected)?.title = "$numItemsSelected Items selected"
                }
            }

            return true
        }

        override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(actionMode: ActionMode?, menuItem: MenuItem?): Boolean {
            return false
        }

        override fun onDestroyActionMode(actionMode: ActionMode?) {
            this@ViewModelFragmentAlarm.actionMode = null
            this@ViewModelFragmentAlarm.isActionModeOn.value = false
        }

    }

    fun populateDataSource(){
        Log.i(TAG, "populateDataSource")
        if( dataSource.value != null ){
            dataSource.value?.apply {
                add(AlarmItem("09:10", "Daily| Alarm in 17 hours 3 minutes", false))
                add(AlarmItem("12:35", "Alarm in 20 hours 37 minutes", false))
                add(AlarmItem("12:35", "Alarm in 20 hours 37 minutes", false))
            }
        }else{
            Log.i(TAG, "populateDataSource fail")
        }

    }

    fun createAdapter(activity: FragmentActivity?):Boolean {
        Log.i(TAG, "createAdapter")
        populateDataSource()
        if(dataSource.value != null ){
            printDataSource(dataSource.value!!)
            this.alarmAdapter = AlarmAdapter(dataSource,this)
            return true
        }
        return false
    }

    private fun printDataSource(arrayList: java.util.ArrayList<AlarmItem>) {
        arrayList.forEach {
            Log.i(TAG, it.toString())
        }

    }


    override fun onCleared() {
        dataSource = MutableLiveData<ArrayList<AlarmItem>>(ArrayList())
        super.onCleared()
    }
    companion object{
        const val TAG = "VmFragmentAlarmLogger"
    }
}