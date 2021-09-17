package com.example.storageroomapp.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayList(
    @PrimaryKey val playListId:Long,
    val playListName:String,
    val userCreatorId:Long
)
