package com.earl.chatprojectboilerplate.data.mappers

import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.CurrentCountryCodeDtoMapper
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import javax.inject.Inject

class BaseCurrentCountryCodeDtoMapper @Inject constructor(): CurrentCountryCodeDtoMapper<CurrentCountryCode> {

    override fun map(code: String, flag: String) = CurrentCountryCode(code, flag)
}