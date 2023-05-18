package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class IsSuccessAuthRequestDto(
    @SerializedName("is_success") val isSuccess: Boolean
)

@Serializable
data class AuthRequestDetails(
    @SerializedName("loc") val loc: String,
    @SerializedName("msg") val msg: String,
    @SerializedName("type") val type: String,
)

@Serializable
data class AuthRequestBody(
    @SerializedName("phone") val phone: String
)
