package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.localdb.samples.SamplesDao
import com.example.planpacifico.data.models.entities.SampleTypeEntity
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.example.planpacifico.data.models.entities.SamplesEntity
import com.example.planpacifico.data.models.entities.relations.SampleParametersAndMeasures
import com.example.planpacifico.domain.samples.SamplesRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class SamplesViewModel(private val repo : SamplesRepo) :ViewModel(){
    fun saveSample(samplesEntity: SamplesEntity): StateFlow<Result<Long>> = flow{
        kotlin.runCatching {
            repo.saveSample(samplesEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getExistingSample(analysis_id: Int, parameter_id: Int): StateFlow<Result<SamplesEntity>> = flow{
        kotlin.runCatching {
            repo.getExistingSample(analysis_id,parameter_id)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getLastSample(): StateFlow<Result<SamplesEntity>> = flow{
        kotlin.runCatching {
            repo.getLastSample()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getSampleByAnalysisId(analysis_id:Int): StateFlow<Result<List<SamplesEntity>>> = flow{
        kotlin.runCatching {
            repo.getSampleByAnalysisId(analysis_id)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getSampleParametersAndMeasures(analysis_id:Int): StateFlow<Result<List<SampleParametersAndMeasures>>> = flow{
        kotlin.runCatching {
            repo.getSampleParametersAndMeasures(analysis_id)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun updateSample(samplesEntity: SamplesEntity): StateFlow<Result<Int>> = flow{
        kotlin.runCatching {
            repo.updateSample(samplesEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

}
class SamplesViewModelFactory(private val repo : SamplesRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SamplesRepo::class.java).newInstance(repo)
    }
}