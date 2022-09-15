package com.example.planpacifico.data.localdb.samples

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import androidx.room.Update
import com.example.planpacifico.data.models.entities.SamplesEntity
import com.example.planpacifico.data.models.entities.relations.SampleAndParameters
import com.example.planpacifico.data.models.entities.relations.SampleParametersAndMeasures

@Dao
interface SamplesDao {

    @Query("SELECT * FROM samplesentity WHERE analysis_id = :analysis_id")
    suspend fun getSampleByAnalysisId(analysis_id:Int): List<SamplesEntity>

    @Query("SELECT * FROM samplesentity WHERE analysis_id = :analysis_id AND parameter_id = :parameter_id")
    suspend fun getExistingSample(analysis_id:Int,parameter_id:Int): SamplesEntity

    @Insert(onConflict = ABORT)
    suspend fun saveSample(SamplesEntity: SamplesEntity):Long

    @Query("SELECT * FROM samplesentity ORDER BY id_sample DESC LIMIT 1 ")
    suspend fun getLastSample():SamplesEntity

    @Query("select * from samplesentity as s join  parameterentity as p on s.parameter_id = p.id_parameter where analysis_id = :id_analysis")
    suspend fun getSampleParametersAndMeasures(id_analysis:Int):List<SampleParametersAndMeasures>

    @Update
    suspend fun updateSample(samplesEntity: SamplesEntity):Int

}