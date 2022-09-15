package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.domain.departments.DepartmentsRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.DepartmentResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class DepartmentsViewModel(private val repo : DepartmentsRepo):ViewModel() {

    fun saveDepartment(departmentEntity: DepartmentEntity):StateFlow<Result<Long>> = flow{
        kotlin.runCatching {
            repo.saveDepartment(departmentEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun fetchRestDepartment():StateFlow<Result<BaseResponse<DepartmentResponse>>> = flow{
        kotlin.runCatching {
            repo.getRestDepartments()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getDbDepartments():StateFlow<Result<List<DepartmentEntity>>> = flow{
        kotlin.runCatching {
            repo.getDbDepartments()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())


}
class DepartmentsViewModelFactory(private val repo : DepartmentsRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DepartmentsRepo::class.java).newInstance(repo)

    }
}
