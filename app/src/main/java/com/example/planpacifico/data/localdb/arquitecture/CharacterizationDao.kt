package com.example.planpacifico.data.localdb.arquitecture

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.planpacifico.data.models.entities.CharacterizationEntity
import com.example.planpacifico.data.models.entities.FeatureEntity
import com.example.planpacifico.data.models.entities.PopulationsEntity

@Dao
interface CharacterizationDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveCharacterization(characterizationEntity: CharacterizationEntity):Long
}