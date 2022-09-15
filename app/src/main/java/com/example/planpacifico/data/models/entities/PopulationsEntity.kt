package com.example.planpacifico.data.models.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class PopulationsEntity(
    @PrimaryKey(autoGenerate = true)
    val id_population: Int,
    val populated_center_id: Int,
    val ethnic_groups: String,
    val length: String,
    val latitude: String,
    val altitude: String,
    val photography: String,
    val inhabitants_number: String,
):Parcelable
