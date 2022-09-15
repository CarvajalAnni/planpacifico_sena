package com.example.planpacifico.domain.measures

import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.MeasureEntity

interface MeasuresRepo {
    suspend fun saveMeasure(measureEntity: MeasureEntity):Long
    suspend fun getMeasuresBySample(sample_id:Int):List<MeasureEntity>

}