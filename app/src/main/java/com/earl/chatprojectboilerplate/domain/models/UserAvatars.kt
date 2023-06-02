package com.earl.chatprojectboilerplate.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserAvatars(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("bigAvatar") val bigAvatar: String,
    @SerializedName("miniAvatar") val miniAvatar: String
)
