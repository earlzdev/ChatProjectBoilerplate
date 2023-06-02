package com.earl.chatprojectboilerplate.domain

import com.earl.chatprojectboilerplate.domain.models.DbResponse
import com.earl.chatprojectboilerplate.domain.models.UpdateUserProfileModel
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun fetchUserProfileData(): Flow<DbResponse<UserProfileData>>

    suspend fun updateProfileInfo(updateModel: UpdateUserProfileModel)
}