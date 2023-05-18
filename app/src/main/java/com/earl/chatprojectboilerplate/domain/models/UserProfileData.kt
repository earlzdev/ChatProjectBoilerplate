package com.earl.chatprojectboilerplate.domain.models

data class UserProfileData(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String,
    val id: Int,
    val last: String,
    val online: Boolean,
    val created: String,
    val phone: String,
    val completed_task: Int,
    val avatars: UserAvatars?,
)