package com.example.planpacifico.domain.populations

import com.example.planpacifico.data.models.entities.PopulationAndPopulatedCenterAndMunicipality
import com.example.planpacifico.data.models.entities.PopulationsEntity
import com.example.planpacifico.data.models.entities.SamplesEntity

interface PopulationsRepo {
    suspend fun getDbPopulations():List<PopulationAndPopulatedCenterAndMunicipality>
    suspend fun savePopulation(populationsEntity: PopulationsEntity):Long
    suspend fun deletePopulation(populationsEntity: PopulationsEntity):Int
    suspend fun getPopulationByPopulatedCenter(id_populated_center:Int):PopulationsEntity

}