package com.earl.chatprojectboilerplate.data

import com.earl.chatprojectboilerplate.data.remoteDataSource.NetworkService
import com.earl.chatprojectboilerplate.domain.MainRepository
import com.earl.chatprojectboilerplate.presentation.utils.log
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
): MainRepository  {

    override suspend fun fetchUserProfileData(token: String) {
        try {
            val data = networkService.fetchUserInfo()
            log("data -> $data")
        } catch (e: Exception) {
            log("e -> $e")
        }
    }
}