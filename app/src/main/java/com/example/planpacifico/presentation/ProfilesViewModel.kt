package com.example.planpacifico.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.planpacifico.data.models.entities.ProfileEntity
import com.example.planpacifico.domain.profiles.ProfilesRepo
import com.fabricaswsenactpi.com.construyendopacifico.core.Result
import com.fabricaswsenactpi.com.construyendopacifico.data.models.web.BaseResponse
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ProfilesViewModel(private val repo : ProfilesRepo):ViewModel() {

    fun getRestProfiles(): StateFlow<Result<BaseResponse<ProfileEntity>>> = flow {
        kotlin.runCatching {
            repo.getRestProfiles()
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())

    fun saveProfile(profileEntity: ProfileEntity): StateFlow<Result<Long>> = flow {
        kotlin.runCatching {
            repo.saveProfile(profileEntity)
        }.onSuccess {
            emit(Result.Success(it))
        }.onFailure {
            emit(Result.Failure(Exception(it.message)))
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Result.Loading())
}
class ProfilesViewModelFactory(private val repo : ProfilesRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProfilesRepo::class.java).newInstance(repo)
    }
}