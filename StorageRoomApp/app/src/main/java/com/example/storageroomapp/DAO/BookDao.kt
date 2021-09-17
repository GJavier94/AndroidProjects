package com.example.storageroomapp.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storageroomapp.Entities.Book

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg books: Book)


    /**
     * Query multiple tables
     * But what if we want to query  multiple tables?
     * 1.- use Query annotation -> write the string sql query -> with inner joins
     *
     * for example let's define a relationship between new entities
     * ===>Book
     * in this case what the query returns is a Book and we just use the Book entity
     */

    @Query("select * from Book inner join User on User.uid = Book.user_id where User.first_name = :userName")
    fun findBooksByNameSync(userName: String): List<Book>

}