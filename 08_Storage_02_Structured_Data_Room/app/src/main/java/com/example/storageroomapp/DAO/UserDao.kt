package com.example.storageroomapp.DAO

import androidx.room.*
import com.example.storageroomapp.Entities.NameTuple
import com.example.storageroomapp.Entities.Relationships.UserWithPlayListsAndSongs
import com.example.storageroomapp.Entities.User

/**
 * How you interact with the data that is stored in the entities created ?
 * By using another abstraction called DAO -> data access object
 * it is either an interface or abstract class that defines CRUD operation over the entities
 * but there's only the signature of the methods in addition with some anotation
 * which specifies the operation to be executed
 *
 * That implies that Room library on runtime is in charge to generate a class which implements or inherits the
 * DAO and provide the implementation of the methods
 *
 * The annotations can be divided in  two types:
 *      1.- Convinience methods CUD (create{insert}, update, delete)
 *          this operations have to be simple @insert,@update, @delete
 *      2.- Query operations let's you define into an string an SQL complex query
 *          that let's you execute CRUD operations, @Query
 *
 */
@Dao
interface UserDao {
    /*
    You can define one or more arguments, and each argument can be
        1.- an instance of the entity (example: User)
        2.- a collection of instances of the entity
        also in cases where there is a conflict in the insert operation
        you can define what to do in that cases by using arguments in the insert annotation

        return -> it can return the newId or a list(newId's) in case there were many insertions
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE) // this flag tells to replace the existing data with the new one
    fun insertAll(vararg users:User)

    /*
    for CUD operations it uses the id to delete, to update a User instance row on the table

    Room uses the primary key to match passed entity instances to rows in the database.
    If there is no row with the same primary key, Room makes no changes.
     */
    @Update
    fun updateWithId(user:User):Int // it gives you back the id user's who was  updated


    /*
    Room uses the primary key to match passed entity instances to rows in the database.
    If there is no row with the same primary key, Room makes no changes.
     */
    @Delete
    fun delete(user:User):Int

    /*
    Query methods
    allows you to write SQL statements and expose them as DAO methods.
    for:
    1.- queries
    2.- to perform more complex inserts, updates, and deletes.
     */

    @Query("select * from User")
    fun getAll():List<User>

    @Query("select * from User where uid in (:uids)")
    fun loadAllByIds(uids:IntArray):List<User>

    @Query("select * from User where first_name = :first  and second_name = :second limit 1")
    fun findByName(first:String, second:String):User

    //Return a subset of a table's columns
    //it can also return a subset of columns by the same entities or different entities
    //it is necessary to define a data holder class for the result set
    // in this case I define the data class NameTuple into the entity package
    @Query("select User.first_name, User.second_name from User")
    fun loadFullNames():List<NameTuple>
    /**
     * You can pass a simple parameter to a query
     * fun functionName(param1) --> @Query("- -- - - - - - :param1----") as bind parameter to the query sql string
     * you can also pass a collection to a query ( like if it were a list or an entire field
     * fun functionName(param1:List<String>) @Query("-- -- - - - - - in (:param1)- - - - - -")
     * */

    //defining a user join playlist join song
    //          table1  join1 table2 join2 table3 ---> n-1 joins for n tables
    @Transaction
    @Query("select * from User") // it also execute some other queries to join the three tables
    fun getAllUsersWithPlayListsAndSongs():List<UserWithPlayListsAndSongs>


}







