package com.example.storageroomapp.Entities.Relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.storageroomapp.Entities.PlayList
import com.example.storageroomapp.Entities.User

data class UserWithPlayListsAndSongs(
    @Embedded val user:User,
    @Relation(
        entity = PlayList::class,
        parentColumn = "uid",
        entityColumn = "userCreatorId"
    )
    val playLists: List<PlayListWithSongs>?
)
