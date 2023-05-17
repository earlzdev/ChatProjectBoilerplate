package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.earl.chatprojectboilerplate.data.remoteDataSource.models.UserProfileDataDto
import retrofit2.http.GET

interface NetworkService {

    @GET("/api/v1/users/me/")
    suspend fun fetchUserInfo(): UserProfileDataDto
}