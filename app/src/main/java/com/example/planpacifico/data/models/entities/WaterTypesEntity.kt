package com.example.planpacifico.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WaterTypesEntity(
    @PrimaryKey(autoGenerate = false)
    val id_water_type: Int,
    val water_type_name: String
)