package com.earl.chatprojectboilerplate.data.mappers

import com.earl.chatprojectboilerplate.domain.models.UserAvatars
import javax.inject.Inject

class BaseUserProfileAvatarDtoMapper @Inject constructor(): AvatarsDtoMapper<UserAvatars> {

    override fun map(avatar: String, bigAvatar: String, miniAvatar: String) =
        UserAvatars(avatar, bigAvatar, miniAvatar)
}