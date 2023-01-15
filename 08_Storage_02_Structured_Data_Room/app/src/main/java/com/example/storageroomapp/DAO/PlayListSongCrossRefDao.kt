package com.example.storageroomapp.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.storageroomapp.Entities.PlaylistSongCrossRef

@Dao
interface PlayListSongCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg playlistSongCrossRefs: PlaylistSongCrossRef)
}
