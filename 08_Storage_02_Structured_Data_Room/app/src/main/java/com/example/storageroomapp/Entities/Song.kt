package com.example.storageroomapp.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Song(
    @PrimaryKey val songId:Long,
    val songName:String,
    val artist:String
)
