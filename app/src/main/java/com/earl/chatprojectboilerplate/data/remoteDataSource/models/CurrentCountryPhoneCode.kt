package com.earl.chatprojectboilerplate.data.remoteDataSource.models

import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.CurrentCountryCodeDtoMapper
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentCountryPhoneCode(
    @SerializedName("country_code") val code: String,
    val flag: CurrentCountryFlag
) {
    fun <T> map(mapper: CurrentCountryCodeDtoMapper<T>) =
        mapper.map(code, flag.flag)
}

@Serializable
data class CurrentCountryFlag(
    @SerializedName("img") val flag: String
)
