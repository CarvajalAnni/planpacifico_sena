package com.example.planpacifico.domain.populatedCenters

import com.example.planpacifico.data.models.entities.PopulatedCentersEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.PopulatedCentersResponse

interface PopulatedCentersRepo {
    suspend fun getRestPopulatedCenter(): BaseResponse<PopulatedCentersResponse>
    suspend fun saveDepartment(populatedCentersEntity: PopulatedCentersEntity):Long
    suspend fun getDbPopulatedCenters(id_municipality:Int):List<PopulatedCentersEntity>
}