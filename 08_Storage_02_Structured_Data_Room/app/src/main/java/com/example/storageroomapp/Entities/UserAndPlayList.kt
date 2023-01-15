package com.example.storageroomapp.Entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndPlayList(
    @Embedded val user:User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "userCreatorId"
    )
    val playlistList:List<PlayList>?
)
