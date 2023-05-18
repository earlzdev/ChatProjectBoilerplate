package com.earl.chatprojectboilerplate.presentation.auth

import androidx.lifecycle.viewModelScope
import com.earl.chatprojectboilerplate.domain.AuthRepository
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.presentation.utils.BaseViewModel
import com.earl.chatprojectboilerplate.presentation.utils.CoroutinesErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {


    val currentCountryPhoneNumber = flow {
        try {
            emit(authRepository.fetchCurrentCountryPhoneCode())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, ApiResponse.Loading)

    private val _successfulAuthCode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val successfulAuthCode: StateFlow<Boolean> = _successfulAuthCode.asStateFlow()

    private val _accessTokens: MutableStateFlow<ApiResponse<AccessTokens>> = MutableStateFlow(ApiResponse.Initial)
    val accessTokens: StateFlow<ApiResponse<AccessTokens>> = _accessTokens.asStateFlow()

    fun fetchAuthRequestCode(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val res = authRepository.sendAuthRequest(phone)) {
                true -> {
                    _successfulAuthCode.value = res
                }
                else -> {}
            }
        }
    }

    fun login(phone: String, code: String, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _accessTokens,
        coroutinesErrorHandler
    ) {
        authRepository.login(phone, code)
    }

    fun register(phone: String, name: String, username: String, coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _accessTokens,
        coroutinesErrorHandler
    ) {
        authRepository.registerNewUser(phone, name, username)
    }
}