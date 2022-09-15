package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.models.entities.FeatureEntity
import com.example.planpacifico.data.models.entities.WaterTypesEntity
import com.example.planpacifico.domain.features.FeaturesRepo
import com.example.planpacifico.domain.waterTypes.WaterTypesRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class FeaturesViewModel(private val repo : FeaturesRepo):ViewModel() {
    fun getRestFeatures(): StateFlow<Result<BaseResponse<FeatureEntity>>> = flow {
        kotlin.runCatching {
            repo.getRestFeatures()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun saveFeatures(featureEntity: FeatureEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            repo.saveFeatures(featureEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())
}
class FeaturesViewModelFactory(private val repo : FeaturesRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FeaturesRepo::class.java).newInstance(repo)
    }
}
