package com.example.storageroomapp.Entities


import androidx.room.ColumnInfo

data class NameTuple(
    @ColumnInfo(name = "first_name") var firstName:String?,
    @ColumnInfo(name = "second_name")var secondName:String?
)
