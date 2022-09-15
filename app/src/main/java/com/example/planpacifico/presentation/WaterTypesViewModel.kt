package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.models.entities.ProfileEntity
import com.example.planpacifico.data.models.entities.WaterTypesEntity
import com.example.planpacifico.domain.profiles.ProfilesRepo
import com.example.planpacifico.domain.waterTypes.WaterTypesRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class WaterTypesViewModel(private val repo : WaterTypesRepo):ViewModel(){

    fun getRestWaterTypes(): StateFlow<Result<BaseResponse<WaterTypesEntity>>> = flow {
        kotlin.runCatching {
            repo.getRestWaterTypes()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun saveWaterType(waterTypesEntity: WaterTypesEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            repo.saveWaterType(waterTypesEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())
}
class WaterTypesViewModelFactory(private val repo : WaterTypesRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(WaterTypesRepo::class.java).newInstance(repo)
    }
}