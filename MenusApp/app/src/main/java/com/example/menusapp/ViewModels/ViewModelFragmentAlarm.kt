package com.example.menusapp.ViewModels

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.menusapp.Adapters.AlarmAdapter
import com.example.menusapp.Models.AlarmItem

class ViewModelFragmentAlarm: ViewModel() {

    internal lateinit var alarmAdapter:AlarmAdapter
    internal  var isActionModeOn: Boolean = false


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
            this.alarmAdapter = AlarmAdapter(dataSource.value!!, activity)
            return true
        }
        return false
    }

    private fun printDataSource(arrayList: java.util.ArrayList<AlarmItem>) {
        arrayList.forEach {
            Log.i(TAG, it.toString())
        }

    }

    private var dataSource:MutableLiveData<ArrayList<AlarmItem>> = MutableLiveData<ArrayList<AlarmItem>>(ArrayList())


    override fun onCleared() {
        dataSource = MutableLiveData<ArrayList<AlarmItem>>(ArrayList())
        super.onCleared()
    }
    companion object{
        const val TAG = "VmFragmentAlarmLogger"
    }
}