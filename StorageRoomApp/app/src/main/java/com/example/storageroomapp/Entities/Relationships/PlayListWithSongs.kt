package com.example.storageroomapp.Entities.Relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.storageroomapp.Entities.PlayList
import com.example.storageroomapp.Entities.PlaylistSongCrossRef
import com.example.storageroomapp.Entities.Song

data class PlayListWithSongs(
    @Embedded val playList: PlayList,
    @Relation(
        parentColumn = "playListId",
        entityColumn = "songId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs:List<Song>?
)
