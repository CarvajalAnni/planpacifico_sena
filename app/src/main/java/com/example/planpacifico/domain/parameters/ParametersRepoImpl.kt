package com.example.planpacifico.domain.parameters

import com.example.planpacifico.data.localdb.parameters.ParametersDao
import com.example.planpacifico.data.models.entities.ParameterEntity
import com.example.planpacifico.data.rest.WebService
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.ParameterResponse

class ParametersRepoImpl(private val dao:ParametersDao,private val rest:WebService):ParametersRepo {
    override suspend fun getRestParameters(): BaseResponse<ParameterResponse> = rest.getParameters()
    override suspend fun saveParameter(parameterEntity: ParameterEntity): Long = dao.saveParameter(parameterEntity)
    override suspend fun getDbParameters(): List<ParameterEntity> = dao.getParameters()
}