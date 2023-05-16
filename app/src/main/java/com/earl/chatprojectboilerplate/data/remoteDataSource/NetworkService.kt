package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.earl.chatprojectboilerplate.data.remoteDataSource.models.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface NetworkService {

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

    @POST("/api/v1/users/check-auth-code/")
    suspend fun checkAuthRequest(
        @Body checkDto: CheckAuthCodeDto
    ): AccessTokensDto
}