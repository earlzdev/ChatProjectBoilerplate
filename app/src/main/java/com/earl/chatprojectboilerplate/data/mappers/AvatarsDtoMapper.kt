package com.earl.chatprojectboilerplate.data.mappers

interface AvatarsDtoMapper<T> {

    fun map(
        avatar: String,
        bigAvatar: String,
        miniAvatar: String,
    ): T
}