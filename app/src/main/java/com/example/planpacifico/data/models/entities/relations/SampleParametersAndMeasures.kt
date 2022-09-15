package com.example.planpacifico.data.models.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.MeasureEntity

data class SampleParametersAndMeasures(
    @Embedded
    val sampleParameter : SampleAndParameters,
    @Relation(
        parentColumn = "id_sample",
        entityColumn = "sample_id"
    )
    val measures : List<MeasureEntity>
)
