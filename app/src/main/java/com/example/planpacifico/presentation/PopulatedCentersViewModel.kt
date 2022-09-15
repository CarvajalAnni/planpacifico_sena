package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.domain.populatedCenters.PopulatedCentersRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.example.planpacifico.data.models.entities.PopulatedCentersEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.PopulatedCentersResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class PopulatedCentersViewModel(private val repo : PopulatedCentersRepo):ViewModel() {
     fun getRestPopulatedCenter(): StateFlow<Result<BaseResponse<PopulatedCentersResponse>>> = flow {
        kotlin.runCatching {
            repo.getRestPopulatedCenter()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun savePopulatedCenter(populatedCentersEntity: PopulatedCentersEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            repo.saveDepartment(populatedCentersEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())


    fun getDbPopulatedCenters(id_municipality: Int): StateFlow<Result<List<PopulatedCentersEntity>>> = flow {
        kotlin.runCatching {
            repo.getDbPopulatedCenters(id_municipality)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

}
class PopulatedCentersViewModelFactory(private val repo : PopulatedCentersRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PopulatedCentersRepo::class.java).newInstance(repo)

    }
}