package com.example.planpacifico.data.localdb.users

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.UsersEntity

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveUser(usersEntity: UsersEntity):Long

    @Query("SELECT * FROM usersentity WHERE user_nick = :userNick")
    suspend fun getOneUser(userNick:String):UsersEntity
}