package com.example.planpacifico.domain.waterTypes

import com.example.planpacifico.data.models.entities.WaterTypesEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse

interface WaterTypesRepo {
    suspend fun getRestWaterTypes(): BaseResponse<WaterTypesEntity>
    suspend fun saveWaterType(waterTypesEntity: WaterTypesEntity):Long

}