package com.example.planpacifico.data.localdb.sampleTypes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.planpacifico.data.models.entities.SampleTypeEntity

@Dao
interface SampleTypesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveDepartment(sampleTypeEntity: SampleTypeEntity):Long
}