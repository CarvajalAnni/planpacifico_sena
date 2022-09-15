package com.example.planpacifico.data.localdb.ethnicGroups

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.planpacifico.data.models.entities.EthnicGroupEntity

@Dao
interface EthnicGroupsDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveEthnicGroup(ethnicGroupEntity: EthnicGroupEntity):Long

    @Query("SELECT * FROM ethnicgroupentity")
    suspend fun getEthnicGroups():List<EthnicGroupEntity>
}