package com.example.planpacifico.domain.parameters

import com.example.planpacifico.data.models.entities.ParameterEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.ParameterResponse

interface ParametersRepo {
    suspend fun getRestParameters(): BaseResponse<ParameterResponse>
    suspend fun saveParameter(parameterEntity: ParameterEntity):Long
    suspend fun getDbParameters():List<ParameterEntity>
}