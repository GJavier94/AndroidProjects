package com.example.storageroomapp.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.storageroomapp.Entities.User

@Dao
interface UserDao {

    @Query("select * from User")
    fun getAll():List<User>

    @Query("select * from User where uid in (:uids)")
    fun loadAllByIds(uids:IntArray):List<User>

    @Query("select * from User where first_name = :first  and second_name = :second limit 1")
    fun findByName(first:String, second:String):User

    @Insert
    fun insertAll(vararg users:User)

    @Delete
    fun delete(user:User)

}




