package com.earl.chatprojectboilerplate.domain

import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun fetchUserProfileData(): Flow<ApiResponse<UserProfileData>>
}