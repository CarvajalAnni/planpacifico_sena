package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.models.entities.PopulationAndPopulatedCenterAndMunicipality
import com.example.planpacifico.data.models.entities.PopulationsEntity
import com.example.planpacifico.domain.populations.PopulationsRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class PopulationsViewModel(private val repo : PopulationsRepo):ViewModel() {
    fun getDbPopulations(): StateFlow<Result<List<PopulationAndPopulatedCenterAndMunicipality>>> = flow{
        kotlin.runCatching {
            repo.getDbPopulations()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun savePopulation(populationsEntity: PopulationsEntity): StateFlow<Result<Long>> = flow{
        kotlin.runCatching {
            repo.savePopulation(populationsEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun deletePopulation(populationsEntity: PopulationsEntity): StateFlow<Result<Int>> = flow{
        kotlin.runCatching {
            repo.deletePopulation(populationsEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getPopulationByPopulatedCenter(id_populated_center:Int): StateFlow<Result<PopulationsEntity>> = flow{
        kotlin.runCatching {
            repo.getPopulationByPopulatedCenter(id_populated_center)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())


}
class PopulationsViewModelFactory(private val repo : PopulationsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PopulationsRepo::class.java).newInstance(repo)

    }
}

