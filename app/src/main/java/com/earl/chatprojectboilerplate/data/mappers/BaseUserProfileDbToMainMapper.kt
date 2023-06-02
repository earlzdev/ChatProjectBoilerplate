package com.earl.chatprojectboilerplate.data.mappers

import com.earl.chatprojectboilerplate.data.localDataSource.mappers.UserProfileDbToMainMapper
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
import javax.inject.Inject

class BaseUserProfileDbToMainMapper @Inject constructor(): UserProfileDbToMainMapper<UserProfileData> {

    override fun map(
        id: Int,
        name: String,
        username: String,
        phone: String,
        city: String,
        birthday: String,
        about: String,
        vk: String,
        inst: String,
        avatar: String,
    ) = UserProfileData(
        id,
        name,
        username,
        phone,
        city,
        birthday,
        about,
        vk,
        inst,
        avatar
    )
}