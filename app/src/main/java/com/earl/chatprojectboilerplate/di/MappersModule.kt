package com.earl.chatprojectboilerplate.di

import com.earl.chatprojectboilerplate.data.localDataSource.UserProfileInfoDb
import com.earl.chatprojectboilerplate.data.localDataSource.mappers.UserProfileDbToMainMapper
import com.earl.chatprojectboilerplate.data.mappers.*
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.*
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import com.earl.chatprojectboilerplate.domain.models.UserAvatars
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.UserProfileDataDto
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    @Singleton
    fun provideCurrentCountryCodeDtoMapper(): CurrentCountryCodeDtoMapper<CurrentCountryCode> {
        return BaseCurrentCountryCodeDtoMapper()
    }

    @Provides
    @Singleton
    fun provideAccessTokensDtoMapper(): AccessTokensDtoMapper<AccessTokens> {
        return BaseUserAccessTokensDtoMapper()
    }

    @Provides
    @Singleton
    fun provideUserAvatarsDtoMapper(): AvatarsDtoMapper<UserAvatars> {
        return BaseUserProfileAvatarDtoMapper()
    }

    @Provides
    @Singleton
    fun provideUserProfileRemoteToMainMapper(): UserProfileDataRemoteToMainMapper<UserProfileData> {
        return BaseUserProfileRemoteToMainMapper()
    }

    @Provides
    @Singleton
    fun provideUserProfileRemoteToDbMapper(): UserProfileRemoteToDbMapper<UserProfileInfoDb> {
        return BaseUserProfileRemoteToDbMapper()
    }

    @Provides
    @Singleton
    fun provideUserProfileDbToMainMapper(): UserProfileDbToMainMapper<UserProfileData> {
        return BaseUserProfileDbToMainMapper()
    }
}