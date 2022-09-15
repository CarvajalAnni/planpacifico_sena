package com.example.planpacifico.domain.features

import com.example.planpacifico.data.models.entities.FeatureEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse

interface FeaturesRepo {
    suspend fun getRestFeatures(): BaseResponse<FeatureEntity>
    suspend fun saveFeatures(featureEntity: FeatureEntity):Long
}