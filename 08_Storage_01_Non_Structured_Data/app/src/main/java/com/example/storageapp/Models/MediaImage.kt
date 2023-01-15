package com.example.storageapp.Models

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.VectorDrawable
import android.net.Uri
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.storageapp.R

data class MediaImage(
    internal var context:Context,
    internal var contentUri:Uri,
    internal var id:Long,
    internal var description:String?,
    internal var thumbnail:Bitmap = (ResourcesCompat.getDrawable(context.resources, R.drawable.ic_baseline_image_24, null) as VectorDrawable).toBitmap()

)