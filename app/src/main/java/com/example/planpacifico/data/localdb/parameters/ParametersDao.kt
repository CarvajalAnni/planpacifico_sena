package com.example.planpacifico.data.localdb.parameters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.planpacifico.data.models.entities.ParameterEntity

@Dao
interface ParametersDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveParameter(parameterEntity: ParameterEntity):Long

    @Query("SELECT * FROM parameterentity")
    suspend fun getParameters():List<ParameterEntity>

}