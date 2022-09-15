package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.domain.users.UsersRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.room.UsersEntity
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class UsersViewModel(private val repo : UsersRepo) :ViewModel(){
    fun getRestUsers(): StateFlow<Result<BaseResponse<UsersEntity>>> = flow {
        kotlin.runCatching {
            repo.getRestUsers()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun saveUser(usersEntity: UsersEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            repo.saveUser(usersEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun getOneUser(userNick: String): StateFlow<Result<UsersEntity>> = flow {
        kotlin.runCatching {
            repo.getOneUser(userNick)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

}
class UsersViewModelFactory(private val repo : UsersRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UsersRepo::class.java).newInstance(repo)
    }
}