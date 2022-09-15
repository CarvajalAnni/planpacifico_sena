package com.example.planpacifico.domain.departments

import com.example.planpacifico.data.localdb.departments.DepartmentsDao
import com.example.planpacifico.data.rest.WebService
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.DepartmentResponse

class DepartmentsRepoImpl(private val dao: DepartmentsDao,private val rest : WebService):DepartmentsRepo {
    override suspend fun saveDepartment(departmentEntity: DepartmentEntity):Long = dao.saveDepartment(departmentEntity)
    override suspend fun getRestDepartments(): BaseResponse<DepartmentResponse> = rest.getDepartments()
    override suspend fun getDbDepartments(): List<DepartmentEntity> = dao.getDepartments()
}