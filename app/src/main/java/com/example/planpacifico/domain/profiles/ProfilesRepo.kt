package com.example.planpacifico.domain.profiles

import com.example.planpacifico.data.models.entities.ProfileEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse

interface ProfilesRepo {
    suspend fun getRestProfiles(): BaseResponse<ProfileEntity>
    suspend fun saveProfile(profileEntity: ProfileEntity):Long
}