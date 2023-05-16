package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.AccessTokensDtoMapper
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AccessTokensDto(
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("is_user_exists") val isUserExists: Boolean
) {
    fun <T> map(mapper: AccessTokensDtoMapper<T>) =
        mapper.map(refreshToken, accessToken, userId, isUserExists)
}