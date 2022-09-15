package com.example.planpacifico.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Characterization2Entity (
    @PrimaryKey(autoGenerate = true)
    val id_arquitectura_caracterizacion2 : Int,
    var pregunta18 : String,
    var pregunta19 : String,
    var pregunta20 : String,
    var pregunta21 : String,
    var pregunta22 : String,
    var pregunta23 : String,
    var pregunta24 : String,
    var pregunta25 : String,
    var pregunta26 : String,
    var pregunta27 : String,
    var pregunta28 : String,
    var pregunta29 : String,
    var pregunta30 : String,
    var pregunta31 : String,
    var pregunta32 : String,
    var pregunta33 : String,
    var pregunta34 : String,
    var pregunta35 : String
    )