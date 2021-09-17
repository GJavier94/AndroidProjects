package com.example.storageroomapp.Models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storageroomapp.DAO.BookDao
import com.example.storageroomapp.Entities.User
import com.example.storageroomapp.DAO.UserDao
import com.example.storageroomapp.Entities.Book

// it needs to know all the entities that are within this class in an array with the classNames objects
// this attribute is entities

@Database(entities = arrayOf(User::class, Book::class), version= 1 )
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDAO(): UserDao
    abstract fun bookDAO(): BookDao
}
