package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.models.entities.FeatureEntity
import com.example.planpacifico.data.models.entities.ParameterEntity
import com.example.planpacifico.domain.features.FeaturesRepo
import com.example.planpacifico.domain.parameters.ParametersRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.ParameterResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ParametersViewModel(private val repo : ParametersRepo):ViewModel() {
    fun getRestParameters(): StateFlow<Result<BaseResponse<ParameterResponse>>> = flow {
        kotlin.runCatching {
            repo.getRestParameters()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun saveParameter(parameterEntity: ParameterEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            repo.saveParameter(parameterEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getDbParameters(): StateFlow<Result<List<ParameterEntity>>> = flow {
        kotlin.runCatching {
            repo.getDbParameters()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())
}
class ParametersViewModelFactory(private val repo : ParametersRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ParametersRepo::class.java).newInstance(repo)
    }
}
