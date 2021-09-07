package com.example.menusapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.menusapp.Models.AlarmItem
import com.example.menusapp.R
import com.example.menusapp.ViewModels.ViewModelFragmentAlarm

class AlarmAdapter( val dataSource: ArrayList<AlarmItem>, val viewModelFragmentAlarm: ViewModelFragmentAlarm) :RecyclerView.Adapter<AlarmAdapter.ViewHolderAlarm>() {

    inner class ViewHolderAlarm(itemView: View):RecyclerView.ViewHolder(itemView){
        internal var timeTextView:TextView = itemView.findViewById<TextView>(R.id.alarm_item_text_hour)
        internal var detailsTextView:TextView = itemView.findViewById<TextView>(R.id.alarm_item_text_details)
        internal var switch:Switch = itemView.findViewById<Switch>(R.id.alarm_item_switchAlarm)

        init{
            itemView.setOnLongClickListener {
                view ->
                if(!viewModelFragmentAlarm.isActionModeOn.value!!){
                    viewModelFragmentAlarm.isActionModeOn.value = true
                }
                true
            }
            /*
            itemView.setOnTouchListener { v, event ->
                itemView.setBackgroundColor(Color.GRAY)
                true
            }
            */
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
        viewHolder.timeTextView.text = dataSource[position].time
        viewHolder.detailsTextView.text = dataSource[position].details
        viewHolder.switch.isChecked = dataSource[position].switch
        viewHolder.switch.setOnCheckedChangeListener { _, isChecked ->
            dataSource[position].switch = isChecked
        }

    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
    companion object{
        const val TAG  = "AlarmAdapterLogger"
    }
}


