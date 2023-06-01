package com.earl.chatprojectboilerplate.data.localDataSource.mappers

interface UserProfileDbToMainMapper<T> {

    fun map(
        id: Int,
        username: String,
        phone: String,
        city: String,
        birthday: String,
        about: String
    ): T
}