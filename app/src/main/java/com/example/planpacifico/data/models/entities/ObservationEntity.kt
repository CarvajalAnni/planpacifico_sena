package com.example.planpacifico.data.models.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity
data class ObservationEntity (
    @PrimaryKey(autoGenerate = true)
    val id_observation: Int,
    val name_user: String,
    val length: String,
    val latitude: String,
    var pregunta1 : String,
    var pregunta3 : String,
    var pregunta4 : String,
    var pregunta5 : String,
    var pregunta6 : String,
    var pregunta7 : String,
    var pregunta8 : String,
    var pregunta9 : String,
    var pregunta10 : String,
    var pregunta11 : String,
    var pregunta12 : String,
    var pregunta13 : String,
    var pregunta14 : String,
    var pregunta15 : String,
    var pregunta16 : String,


    )