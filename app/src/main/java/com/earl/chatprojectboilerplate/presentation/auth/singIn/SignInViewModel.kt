package com.earl.chatprojectboilerplate.presentation.auth.singIn

import androidx.lifecycle.viewModelScope
import com.earl.chatprojectboilerplate.domain.AuthRepository
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.presentation.utils.BaseViewModel
import com.earl.chatprojectboilerplate.presentation.utils.CoroutinesErrorHandler
import com.earl.chatprojectboilerplate.presentation.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
): BaseViewModel() {


}