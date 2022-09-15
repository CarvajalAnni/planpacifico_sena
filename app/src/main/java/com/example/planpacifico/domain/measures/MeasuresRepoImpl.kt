package com.example.planpacifico.domain.measures

import com.example.planpacifico.data.localdb.measures.MeasuresDao
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.MeasureEntity

class MeasuresRepoImpl(private val dao:MeasuresDao) :MeasuresRepo{
    override suspend fun saveMeasure(measureEntity: MeasureEntity): Long = dao.saveMeasure(measureEntity)
    override suspend fun getMeasuresBySample(sample_id: Int): List<MeasureEntity> = dao.getMeasuresBySample(sample_id)
}