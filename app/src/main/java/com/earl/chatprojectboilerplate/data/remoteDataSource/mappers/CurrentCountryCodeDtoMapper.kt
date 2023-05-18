package com.earl.chatprojectboilerplate.data.remoteDataSource.mappers

interface CurrentCountryCodeDtoMapper<T> {

    fun map(
        code: String,
        flag: String
    ): T
}