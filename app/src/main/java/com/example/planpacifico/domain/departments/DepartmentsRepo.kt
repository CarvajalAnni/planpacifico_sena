package com.example.planpacifico.domain.departments

import androidx.room.Query
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.DepartmentResponse

interface DepartmentsRepo {
    suspend fun saveDepartment(departmentEntity: DepartmentEntity):Long
    suspend fun getRestDepartments(): BaseResponse<DepartmentResponse>
    suspend fun getDbDepartments():List<DepartmentEntity>
}