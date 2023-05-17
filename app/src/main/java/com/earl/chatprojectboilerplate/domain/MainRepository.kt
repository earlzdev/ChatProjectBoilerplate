package com.earl.chatprojectboilerplate.domain

interface MainRepository {

    suspend fun fetchUserProfileData(token: String)
}