package com.earl.chatprojectboilerplate.data.remoteDataSource.mappers

interface AccessTokensDtoMapper<T> {

    fun map(
        refresh: String,
        access: String,
        userId: Int,
        isUserExists: Boolean
    ): T
}