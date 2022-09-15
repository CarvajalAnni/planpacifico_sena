package com.example.planpacifico.domain.municipalities

import com.example.planpacifico.data.localdb.municipalities.MunicipalitiesDao
import com.example.planpacifico.data.models.entities.MunicipalitiesEntity
import com.example.planpacifico.data.rest.WebService
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.MunicipalitiesResponse

class MunicipalitiesRepoImpl(private val dao : MunicipalitiesDao,private val rest : WebService):MunicipalitiesRepo {
    override suspend fun getRestMunicipalities(): BaseResponse<MunicipalitiesResponse> = rest.getAllMunicipalities()
    override suspend fun saveDepartment(municipalitiesEntity: MunicipalitiesEntity): Long = dao.saveDepartment(municipalitiesEntity)
    override suspend fun getDbDepartments(id_department:Int):List<MunicipalitiesEntity> = dao.getDepartments(id_department)
}