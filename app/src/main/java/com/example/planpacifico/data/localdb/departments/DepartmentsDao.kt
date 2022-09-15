package com.example.planpacifico.data.localdb.departments

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import retrofit2.http.GET

@Dao
interface DepartmentsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveDepartment(departmentEntity: DepartmentEntity):Long

    @Query("SELECT * FROM departmententity")
    suspend fun getDepartments():List<DepartmentEntity>
}