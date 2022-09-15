package com.example.planpacifico.domain.samples

import com.example.planpacifico.data.models.entities.SampleTypeEntity
import com.example.planpacifico.data.models.entities.SamplesEntity
import com.example.planpacifico.data.models.entities.relations.SampleParametersAndMeasures

interface SamplesRepo {
    suspend fun getSampleByAnalysisId(analysis_id:Int): List<SamplesEntity>
    suspend fun saveSample(SamplesEntity: SamplesEntity):Long
    suspend fun getExistingSample(analysis_id:Int,parameter_id:Int):SamplesEntity
    suspend fun getLastSample():SamplesEntity
    suspend fun getSampleParametersAndMeasures(id_analysis:Int):List<SampleParametersAndMeasures>
    suspend fun updateSample(samplesEntity: SamplesEntity):Int
}