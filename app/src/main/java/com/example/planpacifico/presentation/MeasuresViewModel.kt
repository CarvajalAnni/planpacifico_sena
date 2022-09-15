package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.models.entities.MunicipalitiesEntity
import com.example.planpacifico.domain.measures.MeasuresRepo
import com.example.planpacifico.domain.municipalities.MunicipalitiesRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.MeasureEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MeasuresViewModel(private val repo:MeasuresRepo):ViewModel() {
    fun saveMeasure(measureEntity: MeasureEntity): StateFlow<Result<Long>> = flow{
        kotlin.runCatching {
            repo.saveMeasure(measureEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getMeasuresBySample(sample_id: Int): StateFlow<Result<List<MeasureEntity>>> = flow{
        kotlin.runCatching {
            repo.getMeasuresBySample(sample_id)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

}
class MeasuresViewModelFactory(private val repo : MeasuresRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MeasuresRepo::class.java).newInstance(repo)
    }
}

