package com.example.storageroomapp.Entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = arrayOf("playListId","songId"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Song::class,
            parentColumns = arrayOf("songId"),
            childColumns = arrayOf("songId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlayList::class,
            parentColumns = arrayOf("playListId"),
            childColumns = arrayOf("playListId"),
            onDelete = ForeignKey.CASCADE
        )
    )

)
data class PlaylistSongCrossRef(
    val playListId: Long,
    val songId:Long
)
