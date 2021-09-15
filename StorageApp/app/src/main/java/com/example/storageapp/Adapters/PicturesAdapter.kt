package com.example.storageapp.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.storageapp.Models.MediaImage
import com.example.storageapp.R

class PicturesAdapter(var dataSource: MutableLiveData<MutableList<MediaImage>>) : RecyclerView.Adapter<PicturesAdapter.PictureViewHolder>() {
    init {
        Log.i(TAG, "Hi im $TAG the size of the dataSource is ${dataSource.value?.size}")
    }
    inner class PictureViewHolder(var itemView: View) :RecyclerView.ViewHolder(itemView){
        internal var imageViewImage: ImageView
        internal var textViewContentUri: TextView
        internal var textViewID: TextView
        internal var textViewDescription: TextView
        init {
            imageViewImage = itemView.findViewById<ImageView>(R.id.imageView_Image)
            textViewContentUri = itemView.findViewById<TextView>(R.id.textView_contentUri)
            textViewID = itemView.findViewById<TextView>(R.id.textView_id)
            textViewDescription = itemView.findViewById<TextView>(R.id.textView_description)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture_recycler_view, parent, false)
        return PictureViewHolder(view)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        this@PicturesAdapter.dataSource.value?.get(position).also {
            mediaImage ->
            if(mediaImage != null){
                holder.imageViewImage.setImageBitmap(mediaImage.thumbnail)
                holder.textViewID.text = mediaImage.id.toString()
                holder.textViewContentUri.text = mediaImage.contentUri.toString()
                holder.textViewDescription.text = mediaImage.description ?: "null"
            }else{
                Log.i(TAG,"onBindViewHolder mediaImage in position: $position couldn't be loaded")
            }

        }

    }

    override fun getItemCount(): Int {
        if( dataSource.value != null)
            return dataSource.value!!.size
        Log.i(TAG,"The datasource is null" )
        return 0 // the data source is null
    }
    companion object{
        const val TAG = "PicturesAdapter"
    }
}
