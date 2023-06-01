package com.earl.chatprojectboilerplate.domain.models

data class UpdateUserProfileModel(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: UpdateAvatarModel
)

data class UpdateAvatarModel(
    val filename: String,
    val base_64: String
)