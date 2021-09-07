package com.example.menusapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.menusapp.Models.AlarmItem
import com.example.menusapp.R
import com.example.menusapp.ViewModels.ViewModelFragmentAlarm

class AlarmAdapter(val dataSource: MutableLiveData<ArrayList<AlarmItem>>, val viewModelFragmentAlarm: ViewModelFragmentAlarm) :RecyclerView.Adapter<AlarmAdapter.ViewHolderAlarm>() {
    var numItemsSelected = MutableLiveData<Int>(0)

    inner class ViewHolderAlarm(itemView: View):RecyclerView.ViewHolder(itemView){
        fun stablishRadioButton(selected: Boolean) {
            if(selected){
                Log.i(TAG,"stablishRadioButton...${ this@AlarmAdapter.numItemsSelected.value}")
                this@AlarmAdapter.numItemsSelected.value = this@AlarmAdapter.numItemsSelected.value?.plus(1)
                radioButtonSelected.setImageResource(R.drawable.ic_baseline_check_circle_24)
            }else{
                Log.i(TAG,"stablishRadioButton...${ this@AlarmAdapter.numItemsSelected.value}")
                this@AlarmAdapter.numItemsSelected.value = this@AlarmAdapter.numItemsSelected.value?.minus(1)
                radioButtonSelected.setImageResource(R.drawable.ic_baseline_circle_24)
            }
        }

        internal var timeTextView:TextView = itemView.findViewById<TextView>(R.id.alarm_item_text_hour)
        internal var detailsTextView:TextView = itemView.findViewById<TextView>(R.id.alarm_item_text_details)
        internal var switch:Switch = itemView.findViewById<Switch>(R.id.alarm_item_switchAlarm)
        internal var radioButtonSelected:ImageView  = itemView.findViewById(R.id.alarm_item_radioButtonSelected)
        init{

            itemView.setOnLongClickListener {
                view ->
                if(!viewModelFragmentAlarm.isActionModeOn.value!!){
                    viewModelFragmentAlarm.isActionModeOn.value = true
                }
                true
            }

            viewModelFragmentAlarm.isActionModeOn.observeForever( Observer {
                    isActionModeOn ->
                when(isActionModeOn){
                    true  -> {
                        switch.visibility = View.INVISIBLE
                        radioButtonSelected.visibility = View.VISIBLE
                    }
                    false ->{
                        switch.visibility = View.VISIBLE
                        radioButtonSelected.setImageResource(R.drawable.ic_baseline_circle_24)
                        radioButtonSelected.visibility = View.INVISIBLE
                    }
                }
            })
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
        viewHolder.timeTextView.text = dataSource.value?.get(position)?.time
        viewHolder.detailsTextView.text = dataSource.value?.get(position)?.details
        viewHolder.switch.isChecked = dataSource.value?.get(position)?.switch!!

        viewHolder.stablishRadioButton(dataSource.value?.get(position)?.isSelected!!)

        viewHolder.switch.setOnCheckedChangeListener { _, isChecked ->
            dataSource.value?.get(position)?.switch = isChecked
        }
        viewHolder.itemView.setOnClickListener {
            dataSource.value?.get(position)?.isSelected = !dataSource.value?.get(position)?.isSelected!!
            viewHolder.stablishRadioButton(dataSource.value?.get(position)?.isSelected!!)
        }

    }

    override fun getItemCount(): Int {
        return dataSource.value?.size!!
    }
    companion object{
        const val TAG  = "AlarmAdapterLogger"
    }
}


