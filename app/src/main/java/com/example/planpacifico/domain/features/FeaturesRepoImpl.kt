package com.example.planpacifico.domain.features

import com.example.planpacifico.data.localdb.features.FeaturesDao
import com.example.planpacifico.data.models.entities.FeatureEntity
import com.example.planpacifico.data.rest.WebService
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse

class FeaturesRepoImpl(private val dao : FeaturesDao,private val rest : WebService) :FeaturesRepo{
    override suspend fun getRestFeatures(): BaseResponse<FeatureEntity> = rest.getFeatures()
    override suspend fun saveFeatures(featureEntity: FeatureEntity): Long = dao.saveFeatures(featureEntity)
}