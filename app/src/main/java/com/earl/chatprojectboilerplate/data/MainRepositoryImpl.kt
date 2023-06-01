package com.earl.chatprojectboilerplate.data

import com.earl.chatprojectboilerplate.data.localDataSource.UserProfileDao
import com.earl.chatprojectboilerplate.data.localDataSource.UserProfileInfoDb
import com.earl.chatprojectboilerplate.data.localDataSource.mappers.UserProfileDbToMainMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.NetworkService
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileDataRemoteToMainMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileRemoteToDbMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.UserProfileResponse
import com.earl.chatprojectboilerplate.data.remoteDataSource.utils.apiRequestFlow
import com.earl.chatprojectboilerplate.domain.MainRepository
import com.earl.chatprojectboilerplate.domain.models.ApiResponse
import com.earl.chatprojectboilerplate.domain.models.DbResponse
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val profileDao: UserProfileDao,
    private val userProfileRemoteToMainMapper: UserProfileDataRemoteToMainMapper<UserProfileData>,
    private val userProfileRemoteToDbMapper: UserProfileRemoteToDbMapper<UserProfileInfoDb>,
    private val userProfileDbToMainMapper: UserProfileDbToMainMapper<UserProfileData>
): MainRepository  {

    private suspend fun fetchRemoteUserProfileData(): Flow<ApiResponse<UserProfileResponse>> = apiRequestFlow {
        networkService.fetchUserInfo()
    }

    override suspend fun fetchUserProfileData(): DbResponse<UserProfileData> {
        return try {
            val localDbResult = profileDao.fetchUserProfileInfo()
            return if (localDbResult == null) {
                when(val remoteData = fetchRemoteUserProfileData().toList().last()) {
                    is ApiResponse.Success -> {
                        profileDao.insertNewUserProfile(remoteData.data.data.mapToDb(userProfileRemoteToDbMapper))
                        DbResponse.Success(remoteData.data.data.map(userProfileRemoteToMainMapper))
                    }
                    is ApiResponse.Failure -> {
                        DbResponse.Fail(remoteData.errorMessage)
                    }
                    else -> {
                        DbResponse.Loading()
                    }
                }
            } else {
                DbResponse.Success(localDbResult.map(userProfileDbToMainMapper))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            DbResponse.Fail(e.message.toString())
        }
    }
}