package com.example.storageroomapp.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid:Int?,
    @ColumnInfo(name = "first_name") val firstName:String?,
    @ColumnInfo(name = "second_name")val secondName:String?,
)