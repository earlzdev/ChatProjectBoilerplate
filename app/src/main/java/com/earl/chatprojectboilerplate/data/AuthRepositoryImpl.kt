package com.earl.chatprojectboilerplate.data

import com.earl.chatprojectboilerplate.data.remoteDataSource.AuthApiService
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.AccessTokensDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.CurrentCountryCodeDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.AuthRequestBody
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.CheckAuthCodeDto
import com.earl.chatprojectboilerplate.data.remoteDataSource.utils.NetworkServiceConfig
import com.earl.chatprojectboilerplate.data.remoteDataSource.utils.authRequestFlow
import com.earl.chatprojectboilerplate.domain.AuthRepository
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import com.earl.chatprojectboilerplate.domain.models.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService,
    private val currentCountryCodeDtoMapper: CurrentCountryCodeDtoMapper<CurrentCountryCode>,
    private val accessTokensDtoMapper: AccessTokensDtoMapper<AccessTokens>
): AuthRepository {

    override suspend fun fetchCurrentCountryPhoneCode(): ApiResponse<CurrentCountryCode> = try {
        val ip = authApiService.fetchCurrentIpAddress(NetworkServiceConfig.Endpoints.CurrentIp().url).ip
        val currentGeo = authApiService.fetchCurrentPhoneNumber(NetworkServiceConfig.Endpoints.CurrentGeo(ip).url)
        ApiResponse.Success(currentGeo.map(currentCountryCodeDtoMapper))
    } catch (e: Exception) {
        val parsedError = parseException(e)
        ApiResponse.Failure(parsedError.message, parsedError.code)
    }

    override suspend fun sendAuthRequest(phone: String): Boolean? = try {
        authApiService.sendAuthRequest(AuthRequestBody(phone)).isSuccess
    } catch (e: Exception) {
        val parsedError = parseException(e)
        ApiResponse.Failure(parsedError.message, parsedError.code)
        null
    }

    override fun login(phone: String, code: String): Flow<ApiResponse<AccessTokens>> = authRequestFlow(accessTokensDtoMapper) {
        authApiService.login(CheckAuthCodeDto(phone, code))
    }

    private fun parseException(ex: Exception) =
        Gson().fromJson(ex.message, ErrorResponse::class.java)
}