package com.example.menusapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.menusapp.DialogFragments.AlarmDelete
import com.example.menusapp.Models.AlarmItem
import com.example.menusapp.R
import com.example.menusapp.ViewModels.ViewModelFragmentAlarm

class AlarmAdapter(val dataSource: MutableLiveData<ArrayList<AlarmItem>>, val viewModelFragmentAlarm: ViewModelFragmentAlarm) :RecyclerView.Adapter<AlarmAdapter.ViewHolderAlarm>() {


    inner class ViewHolderAlarm(itemView: View):RecyclerView.ViewHolder(itemView){

        fun establishRadioButton(selected: Boolean):Boolean {
            if(selected){
                radioButtonSelected.setImageResource(R.drawable.ic_baseline_check_circle_24)
            }else{
                radioButtonSelected.setImageResource(R.drawable.ic_baseline_circle_24)
            }
            return true
        }

        internal var timeTextView:TextView = itemView.findViewById<TextView>(R.id.alarm_item_text_hour)
        internal var detailsTextView:TextView = itemView.findViewById<TextView>(R.id.alarm_item_text_details)
        internal var switch:Switch = itemView.findViewById<Switch>(R.id.alarm_item_switchAlarm)
        internal var radioButtonSelected:ImageView  = itemView.findViewById(R.id.alarm_item_radioButtonSelected)

        init{
            //stablishing listeners and observers

            itemView.setOnLongClickListener {
                view ->
                if(!viewModelFragmentAlarm.isActionModeOn.value!!){
                    viewModelFragmentAlarm.isActionModeOn.value = true
                }
                true
            }


            this@AlarmAdapter.viewModelFragmentAlarm.selectAll.observeForever{
                if(it){
                    establishRadioButton(true)
                }
            }
            this@AlarmAdapter.viewModelFragmentAlarm.unSelectAll.observeForever{
                if(it){
                    establishRadioButton(false)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAlarm {
        Log.i(TAG, "Calling onCreateViewHolder")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.alarm_item,parent,false )
        val viewHolder = ViewHolderAlarm(view)

        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolderAlarm, position: Int) {
        Log.i(TAG, "Calling onBindViewHolder")

        viewModelFragmentAlarm.isActionModeOn.observeForever( Observer {
                isActionModeOn ->
            when(isActionModeOn){
                true  -> {
                    viewHolder.switch.visibility = View.INVISIBLE
                    viewHolder.radioButtonSelected.visibility = View.VISIBLE
                }
                false ->{
                    viewHolder.switch.visibility = View.VISIBLE
                    viewHolder.radioButtonSelected.setImageResource(R.drawable.ic_baseline_circle_24)
                    viewHolder.radioButtonSelected.visibility = View.INVISIBLE
                }
            }
        })


        dataSource.value?.get(position)?.also {
            alarmItem ->

            viewHolder.timeTextView.text = alarmItem?.time
            viewHolder.detailsTextView.text = alarmItem?.details
            viewHolder.switch.isChecked = alarmItem?.switch!!
            viewHolder.establishRadioButton( alarmItem.isSelected)
            viewHolder.itemView.setOnClickListener {
                view ->
                updateAlarmUI(viewHolder, alarmItem)
                createPopUpMenu(view)
            }
        }

        viewHolder.switch.setOnCheckedChangeListener { _, isChecked ->
            dataSource.value?.get(position)?.switch = isChecked
        }


    }

    private fun createPopUpMenu(view: View) {
        if(!viewModelFragmentAlarm.isActionModeOn.value!!){
            Log.i(TAG, "Creating popup when clicking item ")
            val context = viewModelFragmentAlarm.contextFragment

            if(context != null ){
                PopupMenu(context , view).apply {
                    setOnMenuItemClickListener {
                            menuItem ->
                        when(menuItem.itemId){
                            R.id.alarm_item_delete ->{

                                val alarmDialog = AlarmDelete()
                                this@AlarmAdapter.viewModelFragmentAlarm.fragmentManager?.also {
                                        fragmentManager ->
                                    alarmDialog.show(fragmentManager, "delete_alarm")
                                }
                                true
                            }
                            R.id.alarm_item_update -> {
                                Toast.makeText(context, "Update alarm.. ",Toast.LENGTH_SHORT).show()
                                true
                            }
                            else ->{ false }
                        }
                    }
                    inflate(R.menu.popup_alarm_item_menu)
                    show()
                }
            }else{
                Log.i(TAG,"The context is null ")

            }

        }

    }

    private fun updateAlarmUI(viewHolder: ViewHolderAlarm, alarmItem: AlarmItem) {
        if(viewModelFragmentAlarm.isActionModeOn.value!!){
            alarmItem.isSelected = !alarmItem.isSelected
            if(viewHolder.establishRadioButton( alarmItem.isSelected)){
                if(alarmItem.isSelected){
                    viewModelFragmentAlarm.itemCountHandler.addItemToCount()
                }else{
                    viewModelFragmentAlarm.itemCountHandler.removeItemToCount()
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return dataSource.value?.size!!
    }
    companion object{
        const val TAG  = "AlarmAdapterLogger"
    }
}


