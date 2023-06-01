package com.earl.chatprojectboilerplate.domain.models

data class UserProfileData(
    val id: Int,
    val username: String,
    val phone: String,
    val city: String,
    val birthday: String,
    val about: String
)
