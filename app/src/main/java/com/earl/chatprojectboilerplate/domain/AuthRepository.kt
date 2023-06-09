package com.earl.chatprojectboilerplate.domain

import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun fetchCurrentCountryPhoneCode(): ApiResponse<CurrentCountryCode>

    suspend fun sendAuthRequest(phone: String): Boolean?

    fun login(phone: String, code: String): Flow<ApiResponse<AccessTokens>>

    fun registerNewUser(phone: String, name: String, username: String): Flow<ApiResponse<AccessTokens>>
}