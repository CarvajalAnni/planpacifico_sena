package com.example.planpacifico.data.models.web

data class AnalysisBody(
    val user_id: Int,
    val population_id: Int,
    val sample_type_id: Int,
    val water_type_id: Int,
    val date: String,
    val hour: String,
    val sample_type: String,
    val surface_sources: String,
    val underground_sources: String,
    val catchment_type: String
)

