package com.example.planpacifico.domain.users

import com.example.planpacifico.data.localdb.users.UsersDao
import com.example.planpacifico.data.rest.WebService
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.UsersEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse

class UsersRepoImpl(private val dao :UsersDao,private val rest : WebService) :UsersRepo{
    override suspend fun getRestUsers(): BaseResponse<UsersEntity> = rest.getUsers()
    override suspend fun saveUser(usersEntity: UsersEntity): Long = dao.saveUser(usersEntity)
    override suspend fun getOneUser(userNick: String): UsersEntity = dao.getOneUser(userNick)
}