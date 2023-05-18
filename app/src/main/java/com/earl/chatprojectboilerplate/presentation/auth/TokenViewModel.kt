package com.earl.chatprojectboilerplate.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(
    private val tokenManager: TokenManager,
): ViewModel() {

    private val token = MutableLiveData<String?>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.getAccessToken().collect {
                withContext(Dispatchers.Main) {
                    token.value = it
                }
            }
        }
    }

    fun saveTokens(token: AccessTokens) {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.saveAccessToken(token.access)
            tokenManager.saveRefreshToken(token.refresh)
        }
    }

    fun deleteToken() {
        viewModelScope.launch(Dispatchers.IO) {
            tokenManager.deleteAccessToken()
        }
    }
}