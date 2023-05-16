package com.earl.chatprojectboilerplate.domain.models

data class AccessTokens(
    val refresh: String,
    val access: String,
    val userId: Int,
    val isUserExists: Boolean
)
