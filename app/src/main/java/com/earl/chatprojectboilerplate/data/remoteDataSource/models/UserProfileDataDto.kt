package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileDataDto(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("vk") val vk: String?,
    @SerializedName("instagram") val instagram: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("last") val last: String?,
    @SerializedName("online") val online: Boolean,
    @SerializedName("created") val created: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("completed_task") val completed_task: Int,
    @SerializedName("avatars") val avatars: String?,
)