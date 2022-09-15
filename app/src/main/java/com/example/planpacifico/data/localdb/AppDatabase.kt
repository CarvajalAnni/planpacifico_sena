package com.example.planpacifico.data.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.planpacifico.data.localdb.features.FeaturesDao
import com.example.planpacifico.data.localdb.measures.MeasuresDao
import com.example.planpacifico.data.localdb.municipalities.MunicipalitiesDao
import com.example.planpacifico.data.localdb.analysis.AnalysisDao
import com.example.planpacifico.data.localdb.arquitecture.CharacterizationDao
import com.example.planpacifico.data.localdb.departments.DepartmentsDao
import com.example.planpacifico.data.localdb.ethnicGroups.EthnicGroupsDao
import com.example.planpacifico.data.localdb.observation.ObservationDao
import com.example.planpacifico.data.localdb.parameters.ParametersDao
import com.example.planpacifico.data.localdb.populatedCenters.PopulatedCentersDao
import com.example.planpacifico.data.localdb.populations.PopulationsDao
import com.example.planpacifico.data.localdb.profiles.ProfilesDao
import com.example.planpacifico.data.localdb.sampleTypes.SampleTypesDao
import com.example.planpacifico.data.localdb.samples.SamplesDao
import com.example.planpacifico.data.localdb.users.UsersDao
import com.example.planpacifico.data.localdb.waterTypes.WaterTypesDao
import com.example.planpacifico.data.models.entities.*
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.*

@Database(
    entities = arrayOf(
        AnalysisEntity::class,
        DepartmentEntity::class,
        EthnicGroupEntity::class,
        FeatureEntity::class,
        MeasureEntity::class,
        MunicipalitiesEntity::class,
        ParameterEntity::class,
        PopulatedCentersEntity::class,
        PopulationsEntity::class,
        ProfileEntity::class,
        SamplesEntity::class,
        SampleTypeEntity::class,
        UsersEntity::class,
        WaterTypesEntity::class
    ), version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun AnalysisDao(): AnalysisDao
    abstract fun DepartmentsDao(): DepartmentsDao
    abstract fun EthnicGroupsDao(): EthnicGroupsDao
    abstract fun FeaturesDao(): FeaturesDao
    abstract fun MeasuresDao(): MeasuresDao
    abstract fun MunicipalitiesDao(): MunicipalitiesDao
    abstract fun ParametersDao(): ParametersDao
    abstract fun PopulatedCentersDao(): PopulatedCentersDao
    abstract fun PopulationsDao(): PopulationsDao
    abstract fun ProfilesDao(): ProfilesDao
    abstract fun SamplesDao(): SamplesDao
    abstract fun SampleTypesDao(): SampleTypesDao
    abstract fun UsersDao(): UsersDao
    abstract fun WaterTypesDao(): WaterTypesDao


    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "pacifico_database"
            ).build()
            return INSTANCE!!
        }

    }
}