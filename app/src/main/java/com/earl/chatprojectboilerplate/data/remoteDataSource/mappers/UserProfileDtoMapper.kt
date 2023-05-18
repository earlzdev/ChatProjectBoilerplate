package com.earl.chatprojectboilerplate.data.remoteDataSource.mappers

import com.earl.chatprojectboilerplate.data.remoteDataSource.models.AvatarsDto

interface UserProfileDtoMapper<T> {

    fun map(
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
    ): T
}