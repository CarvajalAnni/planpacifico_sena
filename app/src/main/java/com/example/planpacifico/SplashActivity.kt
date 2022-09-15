package com.example.planpacifico

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.planpacifico.data.localdb.AppDatabase
import com.example.planpacifico.data.models.entities.*
import com.example.planpacifico.data.rest.RetrofitClient
import com.example.planpacifico.domain.departments.DepartmentsRepoImpl
import com.example.planpacifico.domain.ethnicGroups.EthnicGroupRepoImpl
import com.example.planpacifico.domain.features.FeaturesRepoImpl
import com.example.planpacifico.domain.municipalities.MunicipalitiesRepoImpl
import com.example.planpacifico.domain.parameters.ParametersRepoImpl
import com.example.planpacifico.domain.populatedCenters.PopulatedCentersRepoImpl
import com.example.planpacifico.domain.profiles.ProfilesRepoImpl
import com.example.planpacifico.domain.users.UsersRepoImpl
import com.example.planpacifico.domain.waterTypes.WaterTypesRepoImpl
import com.example.planpacifico.presentation.*
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import com.example.planpacifico.data.models.entities.PopulatedCentersEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.UsersEntity

class SplashActivity : AppCompatActivity() {
    private val viewModelDepartment by viewModels<DepartmentsViewModel> {
        DepartmentsViewModelFactory(
            DepartmentsRepoImpl(
                AppDatabase.getDatabase(applicationContext).DepartmentsDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelMunicipality by viewModels<MunicipalitiesViewModel> {
        MunicipalitiesViewModelFactory(
            MunicipalitiesRepoImpl(
                AppDatabase.getDatabase(applicationContext).MunicipalitiesDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelPopulatedCenter by viewModels<PopulatedCentersViewModel> {
        PopulatedCentersViewModelFactory(
            PopulatedCentersRepoImpl(
                AppDatabase.getDatabase(applicationContext).PopulatedCentersDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelEthnicGroup by viewModels<EthnicGroupsViewModel> {
        EthnicGroupsViewModelFactory(
            EthnicGroupRepoImpl(
                AppDatabase.getDatabase(applicationContext).EthnicGroupsDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelProfiles by viewModels<ProfilesViewModel> {
        ProfilesViewModelFactory(
            ProfilesRepoImpl(
                AppDatabase.getDatabase(applicationContext).ProfilesDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelUsers by viewModels<UsersViewModel> {
        UsersViewModelFactory(
            UsersRepoImpl(
                AppDatabase.getDatabase(applicationContext).UsersDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelWaterType by viewModels<WaterTypesViewModel> {
        WaterTypesViewModelFactory(
            WaterTypesRepoImpl(
                AppDatabase.getDatabase(applicationContext).WaterTypesDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelFeatures by viewModels<FeaturesViewModel> {
        FeaturesViewModelFactory(
            FeaturesRepoImpl(
                AppDatabase.getDatabase(applicationContext).FeaturesDao(),
                RetrofitClient.webService
            )
        )
    }
    private val viewModelParameters by viewModels<ParametersViewModel> {
        ParametersViewModelFactory(
            ParametersRepoImpl(
                AppDatabase.getDatabase(applicationContext).ParametersDao(),
                RetrofitClient.webService
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.supportActionBar?.hide()


//        saveData()
        startTimer()


    }

    private fun saveData() {
        saveDepartments()
        saveMunicipalities()
        savePopulatedCenters()
        saveEthnicGroup()
        saveProfiles()
        saveUsers()
        saveWaterType()
        saveFeatures()
        saveParameters()
    }

    private fun saveDepartments() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelDepartment.fetchRestDepartment().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelDepartment.saveDepartment(
                                            DepartmentEntity(
                                                r.id_department.toInt(),
                                                r.department_name
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien dep"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local dep"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun saveMunicipalities() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelMunicipality.getRestMunicipalities().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelMunicipality.saveDepartment(
                                            MunicipalitiesEntity(
                                                r.id_municipality,
                                                r.department_id,
                                                r.municipality_name
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien muni"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local muni"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun savePopulatedCenters() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelPopulatedCenter.getRestPopulatedCenter().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelPopulatedCenter.savePopulatedCenter(
                                            PopulatedCentersEntity(
                                                r.id_populated_center,
                                                r.municipality_id,
                                                r.populated_center_name,
                                                r.populated_center_type
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien popu"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local popu"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun saveEthnicGroup() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelEthnicGroup.getRestEthnicGroups().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelEthnicGroup.saveEthnicGroup(
                                            EthnicGroupEntity(
                                                r.id_ethnic_group.toInt(),
                                                r.ethnic_group_name
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien ethn"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local ethn"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun saveProfiles() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelProfiles.getRestProfiles().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelProfiles.saveProfile(
                                            ProfileEntity(
                                                r.id_profile,
                                                r.name_profile
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien prof"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local prof"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun saveUsers() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelUsers.getRestUsers().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelUsers.saveUser(
                                            UsersEntity(
                                                r.id_user,
                                                r.profile_id,
                                                r.user_nick,
                                                "12345",
                                                r.usernames,
                                                r.user_last_names,
                                                r.phone_number,
                                                r.email,
                                                r.identification
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien users"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local users"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun saveWaterType() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelWaterType.getRestWaterTypes().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelWaterType.saveWaterType(
                                            WaterTypesEntity(
                                                r.id_water_type,
                                                r.water_type_name
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien waterty"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local waterty"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun saveFeatures() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelFeatures.getRestFeatures().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelFeatures.saveFeatures(
                                            FeatureEntity(
                                                r.id_feature,
                                                r.feature_name
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien feature"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local feature"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun saveParameters() {
        lifecycleScope.launchWhenCreated {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModelParameters.getRestParameters().collect {
                    when (it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            it.data.results.forEach { r ->
                                lifecycleScope.launchWhenCreated {
                                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                        viewModelParameters.saveParameter(
                                            ParameterEntity(
                                                r.id_parameter,
                                                r.feature_id,
                                                r.sample_type_id,
                                                r.parameter_name,
                                                r.units,
                                                r.expected_value,
                                                r.operator
                                            )
                                        ).collect { rLocal ->
                                            when (rLocal) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "guardado local bien param"
                                                    )
                                                }
                                                is Result.Failure -> {
                                                    Log.e(
                                                        "saveDepartments: ",
                                                        "Error al guardar info local param"
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        is Result.Failure -> {
                            Log.e("saveDepartments: ", it.exception.message.toString())
                        }
                    }
                }
            }
        }
    }

    private fun startTimer() {
        object : CountDownTimer(2000, 100) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                val i = Intent(Intent(this@SplashActivity, LoginActivity::class.java))
                startActivity(i)
                finish()
            }
        }.start()
    }
}