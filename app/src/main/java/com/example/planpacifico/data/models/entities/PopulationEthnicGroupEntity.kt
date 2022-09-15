package com.example.planpacifico.data.models.entities

import androidx.room.PrimaryKey

data class PopulationEthnicGroupEntity(
    @PrimaryKey(autoGenerate = false)
    val id_population_ethnic_group: Int,
    val ethnic_group_id: Int,
    val population_id: Int,
)
