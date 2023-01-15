package com.example.storageroomapp.Entities.Relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.storageroomapp.Entities.PlayList
import com.example.storageroomapp.Entities.PlaylistSongCrossRef
import com.example.storageroomapp.Entities.Song

data class SongWithPlayLists(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "songId",
        entityColumn = "playListId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val playList: List<PlayList>?
)
