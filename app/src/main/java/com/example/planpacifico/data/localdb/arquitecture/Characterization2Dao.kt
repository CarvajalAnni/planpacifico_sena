package com.example.planpacifico.data.localdb.arquitecture

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.planpacifico.data.models.entities.Characterization2Entity


@Dao
interface Characterization2Dao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveCharacterization2(characterization2Entity: Characterization2Entity):Long
}