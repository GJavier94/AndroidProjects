package com.example.storageroomapp.Entities

import androidx.room.*

@Entity
data class Book(
    @PrimaryKey val id:Int?,
    @ColumnInfo(name = "name_book") val nameBook:String?,
    @ColumnInfo(name = "user_id") val userId:Int?
)

/*
data class UserAndBook(
    @Embedded val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "user_id"
    )
    val book: Book
)*/