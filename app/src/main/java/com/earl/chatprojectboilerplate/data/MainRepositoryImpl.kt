package com.earl.chatprojectboilerplate.data

import com.earl.chatprojectboilerplate.data.remoteDataSource.NetworkService
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.UserProfileDataDto
import com.earl.chatprojectboilerplate.data.remoteDataSource.utils.apiRequestFlow
import com.earl.chatprojectboilerplate.domain.MainRepository
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
import com.earl.chatprojectboilerplate.presentation.utils.log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val userProfileDataMapper: UserProfileDtoMapper<UserProfileData>
): MainRepository  {

    override suspend fun fetchUserProfileData(): Flow<ApiResponse<UserProfileData>> = apiRequestFlow(userProfileDataMapper) {
        networkService.fetchUserInfo()
    }
}