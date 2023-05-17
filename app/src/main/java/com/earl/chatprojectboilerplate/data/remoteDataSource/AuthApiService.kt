package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.earl.chatprojectboilerplate.data.remoteDataSource.models.AccessTokensDto
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.CheckAuthCodeDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    suspend fun refreshToken(
        refresh: String
    ): Response<AccessTokensDto>

    @POST("/api/v1/users/check-auth-code/")
    suspend fun login(
        @Body checkDto: CheckAuthCodeDto
    ): Response<AccessTokensDto>
}