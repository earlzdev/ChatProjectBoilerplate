package com.earl.chatprojectboilerplate.data.mappers

import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.AvatarsDto
import com.earl.chatprojectboilerplate.domain.models.UserAvatars
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
import javax.inject.Inject

class BaseUserProfileDataDtoMapper @Inject constructor(
    private val avatarsMapper: AvatarsDtoMapper<UserAvatars>
): UserProfileDtoMapper<UserProfileData> {

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
        avatars: AvatarsDto?,
    ) = UserProfileData(
        name,
        username,
        birthday,
        city,
        vk,
        instagram,
        status,
        avatar,
        id,
        last,
        online,
        created,
        phone,
        completed_task,
        avatars?.map(avatarsMapper)
    )
}