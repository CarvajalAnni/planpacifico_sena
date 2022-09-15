package com.example.planpacifico.domain.analysis

import com.example.planpacifico.data.models.entities.AnalysisEntity

interface AnalysisRepo {
    suspend fun getOneAnalysis(id_population:Int): AnalysisEntity
    suspend fun saveAnalysis(analysisEntity: AnalysisEntity):Long
}