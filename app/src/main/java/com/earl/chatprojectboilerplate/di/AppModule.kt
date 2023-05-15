package com.earl.chatprojectboilerplate.di

import com.earl.chatprojectboilerplate.data.RepositoryImpl
import com.earl.chatprojectboilerplate.data.remoteDataSource.buildNetworkService
import com.earl.chatprojectboilerplate.domain.Repository
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

    ): Repository {
        return RepositoryImpl(

        )
    }

    @Provides
    @Singleton
    fun provideNetworkService() = buildNetworkService()
}