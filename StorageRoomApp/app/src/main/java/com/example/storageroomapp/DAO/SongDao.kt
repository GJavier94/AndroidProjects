package com.example.storageroomapp.DAO

import androidx.room.*
import com.example.storageroomapp.Entities.Song
import com.example.storageroomapp.Entities.Relationships.SongWithPlayLists

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg songs: Song)

    @Query("select * from Song")
    fun getAll():List<Song>

    @Transaction
    @Query("select * from Song")
    fun getSongsWithPlayLists():List<SongWithPlayLists>


}
