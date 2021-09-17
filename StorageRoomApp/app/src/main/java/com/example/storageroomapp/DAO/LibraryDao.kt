package com.example.storageroomapp.DAO

import androidx.room.*
import com.example.storageroomapp.Entities.Library
import com.example.storageroomapp.Entities.UserAndLibrary

@Dao
interface LibraryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg libraries: Library)

    @Query("select * from Library")
    fun getAll():List<Library>


    @Transaction
    @Query("select * from User")
    fun getUsersAndLibraries():List<UserAndLibrary>


}



