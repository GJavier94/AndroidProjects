package com.example.storageapp.Models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.example.storageapp.R

data class MediaImage(
    private var context:Context,
    private var contentUri:Uri,
    private var id:Long,
    private var description:String,
    private var thumbnail:Bitmap = BitmapFactory.decodeResource(context.resources,R.drawable.ic_baseline_image_24) // from resources get the one with this id the bitmapfactory is able to build from it a bitmap

)