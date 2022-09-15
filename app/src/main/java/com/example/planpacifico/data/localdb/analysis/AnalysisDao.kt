package com.example.planpacifico.data.localdb.analysis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.planpacifico.data.models.entities.AnalysisEntity

@Dao
interface AnalysisDao {

    @Insert(onConflict = ABORT)
    suspend fun saveAnalysis(analysisEntity: AnalysisEntity):Long

    @Query("SELECT * FROM analysisentity WHERE population_id = :id_population")
    suspend fun getOneAnalysis(id_population:Int):AnalysisEntity
}