package com.earl.chatprojectboilerplate.domain.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserProfileModel(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("city") val city: String,
    @SerializedName("vk") val vk: String,
    @SerializedName("instagram") val instagram: String,
    @SerializedName("status") val status: String,
    @SerializedName("avatar") val avatar: UpdateAvatarModel
)

@Serializable
data class UpdateAvatarModel(
    @SerializedName("filename")val filename: String,
    @SerializedName("base_64")val base_64: String
)