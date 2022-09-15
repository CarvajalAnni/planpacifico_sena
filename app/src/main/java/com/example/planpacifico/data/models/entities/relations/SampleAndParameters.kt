package com.example.planpacifico.data.models.entities.relations

data class SampleAndParameters (
    val id_sample : Int,
    val parameter_id : Int,
    val analysis_id : Int,
    val average : String,
    val id_parameter : Int,
    val feature_id : Int,
    val sample_type_id : Int,
    val parameter_name : String,
    val units : String,
    val expected_value : String,
    val operator : String,
)