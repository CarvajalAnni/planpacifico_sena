package com.example.planpacifico.data.localdb.measures

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.MeasureEntity

@Dao
interface MeasuresDao {
    @Insert(onConflict = ABORT)
    suspend fun saveMeasure(measureEntity: MeasureEntity):Long

    @Query("SELECT * FROM measureentity where sample_id = :sample_id")
    suspend fun getMeasuresBySample(sample_id:Int):List<MeasureEntity>

}