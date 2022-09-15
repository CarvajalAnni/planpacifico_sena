package com.example.planpacifico.domain.profiles

import com.example.planpacifico.data.localdb.profiles.ProfilesDao
import com.example.planpacifico.data.models.entities.ProfileEntity
import com.example.planpacifico.data.rest.WebService
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse

class ProfilesRepoImpl(private val dao : ProfilesDao,private val rest : WebService):ProfilesRepo{
    override suspend fun getRestProfiles(): BaseResponse<ProfileEntity> = rest.getProfiles()
    override suspend fun saveProfile(profileEntity: ProfileEntity): Long = dao.saveProfile(profileEntity)
}