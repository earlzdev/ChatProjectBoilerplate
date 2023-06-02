package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.earl.chatprojectboilerplate.data.remoteDataSource.models.UserProfileResponse
import com.earl.chatprojectboilerplate.domain.models.UpdateUserProfileModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface NetworkService {

    @GET("/api/v1/users/me/")
    suspend fun fetchUserInfo(): Response<UserProfileResponse>

    @PUT("/api/v1/users/me/")
    suspend fun updateUserProfileInfo(
        @Body updateModel: UpdateUserProfileModel
    )
}