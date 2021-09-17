package com.example.storageroomapp.Models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storageroomapp.DAO.*
import com.example.storageroomapp.Entities.*

// it needs to know all the entities that are within this class in an array with the classNames objects
// this attribute is entities

@Database(entities = arrayOf(User::class, Book::class, Library::class, PlayList::class, Song::class, PlaylistSongCrossRef::class), version= 1 )
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDAO(): UserDao
    abstract fun bookDAO(): BookDao
    abstract fun libraryDAO(): LibraryDao
    abstract fun playListDAO(): PlayListDao
    abstract fun songDAO(): SongDao
    abstract fun playListSongCrossRefDAO(): PlayListSongCrossRefDao
}
