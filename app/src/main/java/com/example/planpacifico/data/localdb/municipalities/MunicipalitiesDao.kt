package com.example.planpacifico.data.localdb.municipalities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.planpacifico.data.models.entities.MunicipalitiesEntity

@Dao
interface MunicipalitiesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveDepartment(municipalitiesEntity: MunicipalitiesEntity):Long

    @Query("SELECT * FROM municipalitiesentity WHERE department_id = :id_department")
    suspend fun getDepartments(id_department:Int):List<MunicipalitiesEntity>
}