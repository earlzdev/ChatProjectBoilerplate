package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileDataRemoteToMainMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileRemoteToDbMapper
import com.earl.chatprojectboilerplate.domain.models.UserAvatars
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class UserProfileResponse(
    @SerializedName("profile_data") val data: UserProfileDataDto
)

@Serializable
data class UserProfileDataDto(
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?,
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
    @SerializedName("avatars") val avatars: UserAvatars?,
) {
    fun <T> map(mapper: UserProfileDataRemoteToMainMapper<T>) =
        mapper.map(
            name ?: "",
            username ?: "",
            birthday ?: "",
            city ?: "",
            vk ?: "",
            instagram ?: "",
            status ?: "",
            avatar ?: "",
            id ?: 0,
            last ?: "",
            online ?: true,
            created ?: "",
            phone ?: "",
            completed_task ?: 0,
            avatar ?: ""
        )

    fun <T> mapToDb(mapper: UserProfileRemoteToDbMapper<T>) =
        mapper.map(
            name ?: "",
            username ?: "",
            birthday ?: "",
            city ?: "",
            vk ?: "",
            instagram ?: "",
            status ?: "",
            avatar ?: "",
            id ?: 0,
            last ?: "",
            online ?: true,
            created ?: "",
            phone ?: "",
            completed_task ?: 0,
            avatar ?: ""
        )
}