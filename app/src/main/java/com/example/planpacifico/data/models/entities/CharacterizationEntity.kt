package com.example.planpacifico.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterizationEntity (
    @PrimaryKey(autoGenerate = true)
    val id_arquitectura_caracterizacion : Int,
    var name_user : String,
    var lenght : String,
    var latitude : String,
    var age : String,
    var occupation : String,
    var genere : String,
    var pregunta1 : String,
    var pregunta2 : String,
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
    var pregunta17 : String,

)

