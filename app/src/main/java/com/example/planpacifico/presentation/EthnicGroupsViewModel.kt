package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.models.entities.EthnicGroupEntity
import com.example.planpacifico.domain.departments.DepartmentsRepo
import com.example.planpacifico.domain.ethnicGroups.EthnicGroupRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.EthnicGroupResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class EthnicGroupsViewModel(private val repo : EthnicGroupRepo):ViewModel() {
    fun getRestEthnicGroups(): StateFlow<Result<BaseResponse<EthnicGroupResponse>>> = flow {
        kotlin.runCatching {
            repo.getRestEthnicGroups()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun saveEthnicGroup(ethnicGroupEntity: EthnicGroupEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            repo.saveEthnicGroup(ethnicGroupEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getDbEthnicGroups(): StateFlow<Result<List<EthnicGroupEntity>>> = flow {
        kotlin.runCatching {
            repo.getDbEthnicGroups()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())


}
class EthnicGroupsViewModelFactory(private val repo : EthnicGroupRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(EthnicGroupRepo::class.java).newInstance(repo)
    }
}

