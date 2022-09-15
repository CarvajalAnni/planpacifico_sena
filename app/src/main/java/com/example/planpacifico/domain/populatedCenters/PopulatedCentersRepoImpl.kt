package com.example.planpacifico.domain.populatedCenters

import com.example.planpacifico.data.localdb.populatedCenters.PopulatedCentersDao
import com.example.planpacifico.data.rest.WebService
import com.example.planpacifico.data.models.entities.PopulatedCentersEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.PopulatedCentersResponse

class PopulatedCentersRepoImpl(private val dao : PopulatedCentersDao,private val rest : WebService):PopulatedCentersRepo {
    override suspend fun getRestPopulatedCenter(): BaseResponse<PopulatedCentersResponse> = rest.getAllPopulatedCenter()
    override suspend fun saveDepartment(populatedCentersEntity: PopulatedCentersEntity): Long = dao.saveDepartment(populatedCentersEntity)
    override suspend fun getDbPopulatedCenters(id_municipality: Int): List<PopulatedCentersEntity> = dao.getPopulatedCenters(id_municipality)
}
