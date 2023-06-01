package com.earl.chatprojectboilerplate.data.remoteDataSource.mappers

interface UserProfileDataRemoteToMainMapper<T> {

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
        avatars: String,
    ): T
}