package com.earl.chatprojectboilerplate.di

import com.earl.chatprojectboilerplate.data.RepositoryImpl
import com.earl.chatprojectboilerplate.data.remoteDataSource.NetworkService
import com.earl.chatprojectboilerplate.data.remoteDataSource.buildNetworkService
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.AccessTokensDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.CurrentCountryCodeDtoMapper
import com.earl.chatprojectboilerplate.domain.Repository
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(
        networkService: NetworkService,
        currentCountryCodeDtoMapper: CurrentCountryCodeDtoMapper<CurrentCountryCode>,
        accessTokensDtoMapper: AccessTokensDtoMapper<AccessTokens>
    ): Repository {
        return RepositoryImpl(
            networkService,
            currentCountryCodeDtoMapper,
            accessTokensDtoMapper
        )
    }

    @Provides
    @Singleton
    fun provideNetworkService() = buildNetworkService()
}