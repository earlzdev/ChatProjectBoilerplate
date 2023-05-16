package com.earl.chatprojectboilerplate.presentation.singIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.chatprojectboilerplate.domain.Repository
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.presentation.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    val currentCountryPhoneNumber = flow {
        try {
            emit(repository.fetchCurrentCountryPhoneCode())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ApiResponse.Loading)

    private val _successfulAuthCode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val successfulAuthCode: StateFlow<Boolean> = _successfulAuthCode.asStateFlow()

    fun sendAuthRequestCode(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val res = repository.sendAuthRequest(phone)) {
                true -> {
                    _successfulAuthCode.value = res
                }
                else -> {}
            }
        }
    }

    fun checkAuthCode(phone: String, code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val rs = repository.checkAuthCode(phone, code)
            log("$rs")
        }
    }
}