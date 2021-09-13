package com.example.storageapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.storageapp.R

class DatesLaunchedAdapter(var applicationContext: Context,var dataSource: MutableList<String>) : ArrayAdapter<String>(applicationContext,0, dataSource) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val dateLaunched:String = this.dataSource[position]
        val retView = LayoutInflater.from(this.applicationContext).inflate(R.layout.date_list_item_fragment_app_storage,parent,false)

        val textViewDateLaunched = retView.findViewById<TextView>(R.id.textView_date_app_launched)
        textViewDateLaunched.text = dateLaunched
       return retView
    }

}
