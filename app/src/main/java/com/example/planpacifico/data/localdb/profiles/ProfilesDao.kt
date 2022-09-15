package com.example.planpacifico.data.localdb.profiles

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import com.example.planpacifico.data.models.entities.ProfileEntity

@Dao
interface ProfilesDao {
    @Insert(onConflict = ABORT)
    suspend fun saveProfile(profileEntity: ProfileEntity):Long
}