package com.example.planpacifico.domain.ethnicGroups

import androidx.room.Query
import com.example.planpacifico.data.models.entities.EthnicGroupEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.EthnicGroupResponse

interface EthnicGroupRepo {
    suspend fun getRestEthnicGroups(): BaseResponse<EthnicGroupResponse>
    suspend fun saveEthnicGroup(ethnicGroupEntity: EthnicGroupEntity):Long
    suspend fun getDbEthnicGroups():List<EthnicGroupEntity>
}