package com.earl.chatprojectboilerplate.data

import com.earl.chatprojectboilerplate.data.remoteDataSource.NetworkService
import com.earl.chatprojectboilerplate.data.remoteDataSource.NetworkServiceConfig
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.AccessTokensDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.CurrentCountryCodeDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.AuthRequestBody
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.CheckAuthCodeDto
import com.earl.chatprojectboilerplate.domain.Repository
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import com.earl.chatprojectboilerplate.domain.models.ErrorResponse
import com.earl.chatprojectboilerplate.presentation.utils.log
import com.google.gson.Gson
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val currentCountryCodeDtoMapper: CurrentCountryCodeDtoMapper<CurrentCountryCode>,
    private val accessTokensDtoMapper: AccessTokensDtoMapper<AccessTokens>
): Repository {

    override suspend fun fetchCurrentCountryPhoneCode(): ApiResponse<CurrentCountryCode> = try {
        val ip = networkService.fetchCurrentIpAddress(NetworkServiceConfig.Endpoints.CurrentIp().url).ip
        val currentGeo = networkService.fetchCurrentPhoneNumber(NetworkServiceConfig.Endpoints.CurrentGeo(ip).url)
        ApiResponse.Success(currentGeo.map(currentCountryCodeDtoMapper))
    } catch (e: Exception) {
        val parsedError = parseException(e)
        ApiResponse.Failure(parsedError.message, parsedError.code)
    }

    override suspend fun sendAuthRequest(phone: String): Boolean? = try {
        networkService.sendAuthRequest(AuthRequestBody(phone)).isSuccess
    } catch (e: Exception) {
        val parsedError = parseException(e)
        ApiResponse.Failure(parsedError.message, parsedError.code)
        null
    }

    override suspend fun checkAuthCode(phone: String, code: String): ApiResponse<AccessTokens> = try {
        ApiResponse.Success(
            networkService
                .checkAuthRequest(CheckAuthCodeDto(phone, code))
                .map(accessTokensDtoMapper)
        )
    } catch (e: Exception) {
        val parsedError = parseException(e)
        ApiResponse.Failure(parsedError.message, parsedError.code)
    }

    private fun parseException(ex: Exception) =
        Gson().fromJson(ex.message, ErrorResponse::class.java)
}