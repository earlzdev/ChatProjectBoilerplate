package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.earl.chatprojectboilerplate.data.mappers.AvatarsDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileDtoMapper
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
    @SerializedName("id") val id: Int?,
    @SerializedName("last") val last: String?,
    @SerializedName("online") val online: Boolean?,
    @SerializedName("created") val created: String?,
    @SerializedName("phone") val phone: String?,
    @SerializedName("completed_task") val completed_task: Int?,
    @SerializedName("avatars") val avatars: AvatarsDto?,
) {
    fun <T> map(mapper: UserProfileDtoMapper<T>) =
        mapper.map(
            name,
            username,
            birthday ?: "",
            city ?: "",
            vk ?: "",
            instagram ?: "",
            status ?: "",
            avatar ?: "",
            id ?: 0,
            last ?: "",
            online ?: false,
            created ?: "",
            phone ?: "",
            completed_task ?: 0,
            avatars
        )
}

@Serializable
data class AvatarsDto(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("bigAvatar") val bigAvatar: String,
    @SerializedName("miniAvatar") val miniAvatar: String,
) {
    fun <T> map(mapper: AvatarsDtoMapper<T>) =
        mapper.map(avatar, bigAvatar, miniAvatar)
}