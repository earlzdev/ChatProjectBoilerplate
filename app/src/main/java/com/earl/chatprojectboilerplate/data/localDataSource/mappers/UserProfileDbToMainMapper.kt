package com.earl.chatprojectboilerplate.data.localDataSource.mappers

interface UserProfileDbToMainMapper<T> {

    fun map(
        id: Int,
        name: String,
        username: String,
        phone: String,
        city: String,
        birthday: String,
        about: String,
        vk: String,
        inst: String,
        avatar: String
    ): T
}