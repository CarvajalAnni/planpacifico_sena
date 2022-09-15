package com.example.planpacifico.data.localdb.waterTypes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.planpacifico.data.models.entities.WaterTypesEntity

@Dao
interface WaterTypesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveWaterType(waterTypesEntity: WaterTypesEntity):Long
}