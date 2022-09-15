package com.example.planpacifico.data.localdb.populatedCenters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.planpacifico.data.models.entities.PopulatedCentersEntity

@Dao
interface PopulatedCentersDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveDepartment(populatedCentersEntity: PopulatedCentersEntity):Long

    @Query("SELECT * FROM populatedcentersentity WHERE municipality_id  = :id_municipality")
    suspend fun getPopulatedCenters(id_municipality:Int):List<PopulatedCentersEntity>
}