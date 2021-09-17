package com.example.storageroomapp.Entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SongWithPlayLists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "playListId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playList: List<PlayList>?
)
