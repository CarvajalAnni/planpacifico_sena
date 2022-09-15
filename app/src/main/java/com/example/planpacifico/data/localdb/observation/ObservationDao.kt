package com.example.planpacifico.data.localdb.observation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.planpacifico.data.models.entities.ObservationEntity

@Dao
interface ObservationDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveObservation(observationEntity: ObservationEntity):Long

}