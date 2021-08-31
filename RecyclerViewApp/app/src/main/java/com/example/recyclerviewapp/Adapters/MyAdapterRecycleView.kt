package com.example.recyclerviewapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewapp.R

class MyAdapterRecycleView(val arrayCountries: Array<String>):
    RecyclerView.Adapter<MyAdapterRecycleView.MyViewHolder>() {




    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItem: TextView
        init {
            textViewItem = itemView.findViewById(R.id.textViewItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //This is in charge to create the ViewHolder which holds a view
        Log.i(TAG, "calling onCreateViewHolder...")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_holder, parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.i(TAG, "calling onBindViewHolder...")
        holder.textViewItem.text = arrayCountries[position]
    }

    override fun getItemCount(): Int {
        return arrayCountries.size
    }

    companion object{
        const val TAG = "MyAdapterRecycleView"
    }
}
