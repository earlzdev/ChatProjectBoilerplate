package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CheckAuthCodeDto(
    @SerializedName("phone") val phone: String,
    @SerializedName("code") val code: String
)
