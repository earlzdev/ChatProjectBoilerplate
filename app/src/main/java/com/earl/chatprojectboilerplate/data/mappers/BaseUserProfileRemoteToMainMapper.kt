package com.earl.chatprojectboilerplate.data.mappers

import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileDataRemoteToMainMapper
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
import javax.inject.Inject

class BaseUserProfileRemoteToMainMapper @Inject constructor(): UserProfileDataRemoteToMainMapper<UserProfileData> {

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
    ) = UserProfileData(
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