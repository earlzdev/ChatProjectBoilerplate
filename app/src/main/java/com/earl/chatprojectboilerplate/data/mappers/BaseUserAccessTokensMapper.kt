package com.earl.chatprojectboilerplate.data.mappers

import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.AccessTokensDtoMapper
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import javax.inject.Inject

class BaseUserAccessTokensDtoMapper @Inject constructor(): AccessTokensDtoMapper<AccessTokens> {

    override fun map(
        refresh: String,
        access: String,
        userId: Int,
        isUserExists: Boolean,
    ) = AccessTokens(
        refresh, access, userId, isUserExists
    )
}