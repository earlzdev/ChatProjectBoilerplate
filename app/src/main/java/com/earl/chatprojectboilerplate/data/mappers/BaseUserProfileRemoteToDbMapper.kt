package com.earl.chatprojectboilerplate.data.mappers

import com.earl.chatprojectboilerplate.data.localDataSource.UserProfileInfoDb
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileRemoteToDbMapper
import javax.inject.Inject

class BaseUserProfileRemoteToDbMapper @Inject constructor(): UserProfileRemoteToDbMapper<UserProfileInfoDb> {

    override fun map(
        name: String,
        username: String,
        birthday: String,
        city: String,
        vk: String,
        instagram: String,
        status: String,
        avatar: String,
        id: Int,
        last: String,
        online: Boolean,
        created: String,
        phone: String,
        completed_task: Int,
        avatars: String,
    ) = UserProfileInfoDb(
        id,
        name,
        username,
        phone,
        city,
        birthday,
        status,
        vk,
        instagram,
        avatar
    )
}