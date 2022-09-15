package com.example.planpacifico.domain.waterTypes

import com.example.planpacifico.data.localdb.waterTypes.WaterTypesDao
import com.example.planpacifico.data.models.entities.WaterTypesEntity
import com.example.planpacifico.data.rest.WebService
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse

class WaterTypesRepoImpl(private val dao : WaterTypesDao, private val rest:WebService) :WaterTypesRepo{
    override suspend fun getRestWaterTypes(): BaseResponse<WaterTypesEntity> = rest.getWaterTypes()
    override suspend fun saveWaterType(waterTypesEntity: WaterTypesEntity): Long = dao.saveWaterType(waterTypesEntity)
}