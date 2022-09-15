package com.example.planpacifico.data.localdb.features

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.planpacifico.data.models.entities.FeatureEntity

@Dao
interface FeaturesDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveFeatures(featureEntity: FeatureEntity):Long
}