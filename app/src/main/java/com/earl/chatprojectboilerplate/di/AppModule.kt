package com.earl.chatprojectboilerplate.di

import com.earl.chatprojectboilerplate.data.AuthRepositoryImpl
import com.earl.chatprojectboilerplate.data.MainRepositoryImpl
import com.earl.chatprojectboilerplate.data.remoteDataSource.AuthApiService
import com.earl.chatprojectboilerplate.data.remoteDataSource.NetworkService
import com.earl.chatprojectboilerplate.data.remoteDataSource.utils.buildNetworkService
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.AccessTokensDtoMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.CurrentCountryCodeDtoMapper
import com.earl.chatprojectboilerplate.domain.AuthRepository
import com.earl.chatprojectboilerplate.domain.MainRepository
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApiService: AuthApiService,
        currentCountryCodeDtoMapper: CurrentCountryCodeDtoMapper<CurrentCountryCode>,
        accessTokensDtoMapper: AccessTokensDtoMapper<AccessTokens>
    ): AuthRepository {
        return AuthRepositoryImpl(
            authApiService,
            currentCountryCodeDtoMapper,
            accessTokensDtoMapper
        )
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        networkService: NetworkService
    ): MainRepository = MainRepositoryImpl(
        networkService
    )

    @Provides
    @Singleton
    fun provideNetworkService(
        retrofit: Retrofit.Builder,
        okHttpClient: OkHttpClient,
        gson: Gson
    ) = buildNetworkService(retrofit, okHttpClient, gson)
}