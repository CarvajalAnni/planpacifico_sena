package com.example.planpacifico.domain.populations

import com.example.planpacifico.data.localdb.populations.PopulationsDao
import com.example.planpacifico.data.models.entities.PopulationAndPopulatedCenterAndMunicipality
import com.example.planpacifico.data.models.entities.PopulationsEntity
import com.example.planpacifico.data.models.entities.SamplesEntity
import com.example.planpacifico.data.rest.WebService

class PopulationsRepoImpl(private val dao: PopulationsDao,private val rest: WebService):PopulationsRepo {
    override suspend fun getDbPopulations(): List<PopulationAndPopulatedCenterAndMunicipality> = dao.getPopulations()
    override suspend fun savePopulation(populationsEntity: PopulationsEntity): Long = dao.savePopulation(populationsEntity)
    override suspend fun deletePopulation(populationsEntity: PopulationsEntity): Int = dao.deletePopulation(populationsEntity)
    override suspend fun getPopulationByPopulatedCenter(id_populated_center:Int):PopulationsEntity = dao.getPopulationByPopulatedCenter(id_populated_center)
}