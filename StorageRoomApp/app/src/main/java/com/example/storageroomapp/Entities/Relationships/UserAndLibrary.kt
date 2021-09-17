package com.example.storageroomapp.Entities.Relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.storageroomapp.Entities.Library
import com.example.storageroomapp.Entities.User

/**
 * In my logic some users can not to have libraries that implies that there will be some user with no libraries
 * when doing the join between the two tables library can be null that's why the '?'
 */
data class UserAndLibrary (
    @Embedded val user: User,
    @Relation(
        parentColumn = "uid",
        entityColumn = "userOwnerId"
    )
    val library: Library?
)

/****
 * If i wanted all the libraries and its user owners i changed the direction of the relationship
 * by declaring the other as the embedded and swapping the parent and child ( entity column ) columns
 *
 *data class UserAndLibrary (
 *  @Embedded val library:Library,
 *  @Relation(
 *  parentColumn = "userOwnerId",
 *  entityColumn = "uid"
 *  )
 *  val user:User
 * )
 */
