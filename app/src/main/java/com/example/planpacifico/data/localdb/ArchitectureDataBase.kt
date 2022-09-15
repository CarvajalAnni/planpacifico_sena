package com.example.planpacifico.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.planpacifico.data.localdb.arquitecture.Characterization2Dao
import com.example.planpacifico.data.localdb.arquitecture.CharacterizationDao
import com.example.planpacifico.data.localdb.observation.ObservationDao
import com.example.planpacifico.data.models.entities.*


@Database(
    entities = arrayOf(
        CharacterizationEntity::class,
        Characterization2Entity::class,
        ObservationEntity::class,
    ), version = 1
)
abstract class ArchitectureDataBase : RoomDatabase() {

    abstract fun CharacterizationDao(): CharacterizationDao
    abstract fun Characterization2Dao(): Characterization2Dao
    abstract fun ObservationDao(): ObservationDao

    companion object {

        private var INSTANCE: ArchitectureDataBase? = null

        fun getDatabase(context: Context): ArchitectureDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                ArchitectureDataBase::class.java,
                "pacifico_A_database"
            ).build()
            return INSTANCE!!
        }
    }
}
