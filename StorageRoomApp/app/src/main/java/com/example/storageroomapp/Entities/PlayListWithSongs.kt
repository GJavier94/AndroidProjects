package com.example.storageroomapp.Entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlayListWithSongs(
    @Embedded val playList: PlayList,
    @Relation(
        parentColumn = "playListId",
        entityColumn = "songId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs:List<Song>?
)
