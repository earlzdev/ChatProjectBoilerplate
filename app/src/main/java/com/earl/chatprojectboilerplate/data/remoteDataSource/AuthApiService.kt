package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.earl.chatprojectboilerplate.data.remoteDataSource.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface AuthApiService {

    @POST("/api/v1/users/refresh-token/")
    suspend fun refreshToken(
        @Body refresh: RefreshTokenDto
    ): Response<AccessTokensDto>

    @POST("/api/v1/users/check-auth-code/")
    suspend fun login(
        @Body checkDto: CheckAuthCodeDto
    ): Response<AccessTokensDto>

    @GET
    suspend fun fetchCurrentPhoneNumber(
        @Url url: String,
    ): CurrentCountryPhoneCode

    @GET
    suspend fun fetchCurrentIpAddress(
        @Url url: String
    ): CurrentIpAddressDto

    @POST("/api/v1/users/send-auth-code/")
    suspend fun sendAuthRequest(
        @Body phone: AuthRequestBody
    ): IsSuccessAuthRequestDto
}