package com.example.planpacifico.domain.users

import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.UsersEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse

interface UsersRepo {
    suspend fun getRestUsers(): BaseResponse<UsersEntity>
    suspend fun saveUser(usersEntity: UsersEntity):Long
    suspend fun getOneUser(userNick:String):UsersEntity
}