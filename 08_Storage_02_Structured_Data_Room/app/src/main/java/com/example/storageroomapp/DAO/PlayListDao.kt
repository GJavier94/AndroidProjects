package com.example.storageroomapp.DAO

import androidx.room.*
import com.example.storageroomapp.Entities.PlayList
import com.example.storageroomapp.Entities.Relationships.PlayListWithSongs
import com.example.storageroomapp.Entities.UserAndPlayList

@Dao
interface PlayListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg playlists:PlayList)

    @Query("select * from PlayList")
    fun getAll():List<PlayList>

    @Transaction
    @Query("select * from User")
    fun getUsersAndPlayLists():List<UserAndPlayList>

    @Transaction
    @Query("select * from PlayList")
    fun getPlayListsWithSongs():List<PlayListWithSongs>


}