package com.earl.chatprojectboilerplate.domain

import com.earl.chatprojectboilerplate.domain.models.DbResponse
import com.earl.chatprojectboilerplate.domain.models.UserProfileData

interface MainRepository {

    suspend fun fetchUserProfileData(): DbResponse<UserProfileData>
}