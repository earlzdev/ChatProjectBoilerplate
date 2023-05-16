package com.earl.chatprojectboilerplate.domain

import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode

interface Repository {

    suspend fun fetchCurrentCountryPhoneCode(): ApiResponse<CurrentCountryCode>

    suspend fun sendAuthRequest(phone: String): Boolean?

    suspend fun checkAuthCode(phone: String, code: String): ApiResponse<AccessTokens>
}