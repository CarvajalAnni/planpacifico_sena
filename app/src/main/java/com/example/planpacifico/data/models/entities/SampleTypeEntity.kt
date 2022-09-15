package com.example.planpacifico.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SampleTypeEntity(
    @PrimaryKey(autoGenerate = false)
    val id_sample_type: Int,
    val sample_type_name: String
)
