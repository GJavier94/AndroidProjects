package com.example.storageroomapp.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = arrayOf("uid"),
        childColumns = arrayOf("userOwnerId"),
        onDelete = ForeignKey.CASCADE)])

data class Library (
    @PrimaryKey val libraryId:Long,
                val userOwnerId:Long
    )
