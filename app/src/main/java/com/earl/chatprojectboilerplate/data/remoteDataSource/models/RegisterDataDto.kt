package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterDataDto(
    @SerializedName("phone") val phone: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
)
