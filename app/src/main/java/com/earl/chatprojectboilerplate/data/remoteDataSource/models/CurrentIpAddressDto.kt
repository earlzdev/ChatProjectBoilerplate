package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentIpAddressDto(
    @SerializedName("query") val ip: String,
)
