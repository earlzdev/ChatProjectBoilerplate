package com.earl.chatprojectboilerplate.di

import android.app.Application
import com.earl.chatprojectboilerplate.data.AuthRepositoryImpl
import com.earl.chatprojectboilerplate.data.MainRepositoryImpl
import com.earl.chatprojectboilerplate.data.localDataSource.AppDatabase
import com.earl.chatprojectboilerplate.data.localDataSource.UserProfileDao
import com.earl.chatprojectboilerplate.data.localDataSource.UserProfileInfoDb
import com.earl.chatprojectboilerplate.data.localDataSource.buildAppDatabase
import com.earl.chatprojectboilerplate.data.localDataSource.mappers.UserProfileDbToMainMapper
import com.earl.chatprojectboilerplate.data.remoteDataSource.AuthApiService
import com.earl.chatprojectboilerplate.data.remoteDataSource.NetworkService
import com.earl.chatprojectboilerplate.data.remoteDataSource.mappers.*
import com.earl.chatprojectboilerplate.data.remoteDataSource.utils.buildNetworkService
import com.earl.chatprojectboilerplate.domain.AuthRepository
import com.earl.chatprojectboilerplate.domain.MainRepository
import com.earl.chatprojectboilerplate.domain.models.AccessTokens
import com.earl.chatprojectboilerplate.domain.models.CurrentCountryCode
import com.earl.chatprojectboilerplate.data.remoteDataSource.models.UserProfileDataDto
import com.earl.chatprojectboilerplate.domain.models.UserProfileData
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
        networkService: NetworkService,
        profileDao: UserProfileDao,
        userProfileRemoteToMainMapper: UserProfileDataRemoteToMainMapper<UserProfileData>,
        userProfileRemoteToDbMapper: UserProfileRemoteToDbMapper<UserProfileInfoDb>,
        userProfileDbToMainMapper: UserProfileDbToMainMapper<UserProfileData>
    ): MainRepository = MainRepositoryImpl(
        networkService,
        profileDao,
        userProfileRemoteToMainMapper,
        userProfileRemoteToDbMapper,
        userProfileDbToMainMapper
    )

    @Provides
    @Singleton
    fun provideNetworkService(
        retrofit: Retrofit.Builder,
        okHttpClient: OkHttpClient,
        gson: Gson
    ) = buildNetworkService(retrofit, okHttpClient, gson)

    @Provides
    @Singleton
    fun provideAppDb(app: Application) = buildAppDatabase(app)

    @Provides
    @Singleton
    fun provideUserProfileDao(db: AppDatabase) = db.userProfileDap()
}