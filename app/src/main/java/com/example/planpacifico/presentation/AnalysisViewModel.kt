package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.localdb.analysis.AnalysisDao
import com.example.planpacifico.data.models.entities.AnalysisEntity
import com.example.planpacifico.domain.analysis.AnalysisRepo
import com.example.planpacifico.domain.departments.DepartmentsRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class AnalysisViewModel(private val repo: AnalysisRepo):ViewModel() {
    fun getOneAnalysis(id_population:Int): StateFlow<Result<AnalysisEntity>> = flow{
        kotlin.runCatching {
            repo.getOneAnalysis(id_population)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun saveAnalysis(analysisEntity: AnalysisEntity): StateFlow<Result<Long>> = flow{
        kotlin.runCatching {
            repo.saveAnalysis(analysisEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())


}
class AnalysisViewModelFactory(private val repo : AnalysisRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AnalysisRepo::class.java).newInstance(repo)

    }
}