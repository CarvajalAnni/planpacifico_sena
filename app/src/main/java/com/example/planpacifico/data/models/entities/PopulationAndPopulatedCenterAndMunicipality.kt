package com.example.planpacifico.data.models.entities

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopulationAndPopulatedCenterAndMunicipality(
    val id_population: Int,
    val populated_center_id: Int,
    val ethnic_groups: String,
    val length: String,
    val latitude: String,
    val altitude: String,
    val photography: String,
    val inhabitants_number: String,
    val id_populated_center : Int ,
    val municipality_id : Int ,
    val populated_center_name : String ,
    val populated_center_type : String ,
    val id_municipality: Int,
    val department_id: Int,
    val municipality_name: String
):Parcelable
