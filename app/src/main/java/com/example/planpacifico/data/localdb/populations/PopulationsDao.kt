package com.example.planpacifico.data.localdb.populations

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.example.planpacifico.data.models.entities.PopulationAndPopulatedCenterAndMunicipality
import com.example.planpacifico.data.models.entities.PopulationsEntity
import com.example.planpacifico.data.models.entities.SamplesEntity

@Dao
interface PopulationsDao {
    @Query("select * from populationsentity as p join populatedcentersentity as pc on p.populated_center_id = pc.id_populated_center join municipalitiesentity as m on pc.municipality_id = m.id_municipality ")
    suspend fun getPopulations():List<PopulationAndPopulatedCenterAndMunicipality>

    @Query("SELECT * FROM populationsentity WHERE populated_center_id = :id_populated_center")
    suspend fun getPopulationByPopulatedCenter(id_populated_center:Int): PopulationsEntity

    @Insert(onConflict = ABORT)
    suspend fun savePopulation(populationsEntity: PopulationsEntity):Long

    @Delete
    suspend fun deletePopulation(populationsEntity: PopulationsEntity):Int
}