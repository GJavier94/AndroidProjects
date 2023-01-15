package com.example.storageroomapp.Entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/**
 * If there are more than one attribute -column to be ignored by the Room database Library
 * you can define it into annotation entity class
 */
@Entity(ignoredColumns = arrayOf("picture"))
data class User(
    @PrimaryKey(autoGenerate = true) val uid:Long,
    @ColumnInfo(name = "first_name") val firstName:String,
    @ColumnInfo(name = "second_name")val secondName:String,


){
    /*
    You can also define a property which will be ignored by room by using
    the annotation ignore
     */
    val picture:Bitmap? = null
}



