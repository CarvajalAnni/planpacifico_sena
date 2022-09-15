package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.models.entities.MunicipalitiesEntity
import com.example.planpacifico.domain.municipalities.MunicipalitiesRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.DepartmentEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.MunicipalitiesResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class MunicipalitiesViewModel (private val repo : MunicipalitiesRepo):ViewModel(){

    fun getRestMunicipalities(): StateFlow<Result<BaseResponse<MunicipalitiesResponse>>> = flow{
        kotlin.runCatching {
            repo.getRestMunicipalities()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun saveDepartment(municipalitiesEntity: MunicipalitiesEntity): StateFlow<Result<Long>> = flow{
        kotlin.runCatching {
            repo.saveDepartment(municipalitiesEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getDbDepartments(id_department:Int): StateFlow<Result<List<MunicipalitiesEntity>>> = flow{
        kotlin.runCatching {
            repo.getDbDepartments(id_department)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

}
class MunicipalitiesViewModelFactory(private val repo : MunicipalitiesRepo) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MunicipalitiesRepo::class.java).newInstance(repo)
    }
}

