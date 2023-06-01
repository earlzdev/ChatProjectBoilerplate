package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.earl.chatprojectboilerplate.data.remoteDataSource.models.UserProfileResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("/api/v1/users/me/")
    suspend fun fetchUserInfo(): Response<UserProfileResponse>
}