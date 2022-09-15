package com.example.planpacifico.domain.municipalities

import com.example.planpacifico.data.models.entities.MunicipalitiesEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.MunicipalitiesResponse

interface MunicipalitiesRepo {
    suspend fun getRestMunicipalities(): BaseResponse<MunicipalitiesResponse>
    suspend fun saveDepartment(municipalitiesEntity: MunicipalitiesEntity):Long
    suspend fun getDbDepartments(id_department:Int):List<MunicipalitiesEntity>
}