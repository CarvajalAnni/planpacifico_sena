package com.example.planpacifico.domain.analysis

import com.example.planpacifico.data.localdb.analysis.AnalysisDao
import com.example.planpacifico.data.models.entities.AnalysisEntity

class AnalysisRepoImpl(private val dao: AnalysisDao):AnalysisRepo {
    override suspend fun getOneAnalysis(id_population: Int): AnalysisEntity = dao.getOneAnalysis(id_population)
    override suspend fun saveAnalysis(analysisEntity: AnalysisEntity): Long = dao.saveAnalysis(analysisEntity)
}