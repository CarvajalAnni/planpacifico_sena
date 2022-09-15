package com.example.planpacifico.domain.samples

import com.example.planpacifico.data.localdb.samples.SamplesDao
import com.example.planpacifico.data.models.entities.SampleTypeEntity
import com.example.planpacifico.data.models.entities.SamplesEntity
import com.example.planpacifico.data.models.entities.relations.SampleParametersAndMeasures

class SamplesRepoImpl(private val dao: SamplesDao):SamplesRepo {
    override suspend fun getSampleByAnalysisId(analysis_id:Int): List<SamplesEntity> = dao.getSampleByAnalysisId(analysis_id)
    override suspend fun saveSample(SamplesEntity: SamplesEntity): Long =dao.saveSample(SamplesEntity)
    override suspend fun getExistingSample(analysis_id: Int, parameter_id: Int): SamplesEntity =dao.getExistingSample(analysis_id,parameter_id)
    override suspend fun getLastSample():SamplesEntity = dao.getLastSample()
    override suspend fun getSampleParametersAndMeasures(id_analysis:Int):List<SampleParametersAndMeasures> = dao.getSampleParametersAndMeasures(id_analysis)
    override suspend fun updateSample(samplesEntity: SamplesEntity):Int = dao.updateSample(samplesEntity)
}