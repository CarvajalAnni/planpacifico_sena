package com.example.planpacifico.domain.ethnicGroups

import com.example.planpacifico.data.localdb.ethnicGroups.EthnicGroupsDao
import com.example.planpacifico.data.models.entities.EthnicGroupEntity
import com.example.planpacifico.data.rest.WebService
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.EthnicGroupResponse

class EthnicGroupRepoImpl(private val dao : EthnicGroupsDao, private val rest : WebService):EthnicGroupRepo {
    override suspend fun getRestEthnicGroups(): BaseResponse<EthnicGroupResponse> = rest.getEthnicGroups()


    override suspend fun saveEthnicGroup(ethnicGroupEntity: EthnicGroupEntity): Long =dao.saveEthnicGroup(ethnicGroupEntity)
    override suspend fun getDbEthnicGroups(): List<EthnicGroupEntity> = dao.getEthnicGroups()
}