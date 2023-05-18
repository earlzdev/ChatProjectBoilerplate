package com.earl.chatprojectboilerplate.di

import com.earl.chatprojectboilerplate.data.mappers.*
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.AccessTokensDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.CurrentCountryCodeDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.UserProfileDtoMapper
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import com.earl.chatprojectboilerplate.domain.models.UserAvatars
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
    fun provideUserProfileDataDtoMapper(
        avatarsMapper: AvatarsDtoMapper<UserAvatars>
    ): UserProfileDtoMapper<UserProfileData> {
        return BaseUserProfileDataDtoMapper(avatarsMapper)
    }
}