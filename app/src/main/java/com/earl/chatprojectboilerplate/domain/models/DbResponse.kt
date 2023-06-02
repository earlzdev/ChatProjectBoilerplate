package com.earl.chatprojectboilerplate.domain.models

sealed class DbResponse<T> {

    class Loading<T>: DbResponse<T>()

    data class Success<T>(val value: T): DbResponse<T>()

    data class Fail<T>(val error: String): DbResponse<T>()
}
