package com.earl.chatprojectboilerplate.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.earl.chatprojectboilerplate.data.remoteDataSource.*
import com.earl.chatprojectboilerplate.data.remoteDataSource.utils.*
import com.earl.chatprojectboilerplate.domain.models.TokenManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")
@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideAuthAuthenticator(tokenManager: TokenManager): AuthAuthenticator = provideAuthenticator(tokenManager)

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor = provideAuthenticateInterceptor(tokenManager)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = provideLoggingInterceptor()

    @Singleton
    @Provides
    fun provideGson(): Gson = provideGsonConverter()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder = provideRetrofitBuilder()

    @Singleton
    @Provides
    fun provideHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        authenticator: AuthAuthenticator,
    ): OkHttpClient = provideOkHttpClient(
        authInterceptor,
        loggingInterceptor,
        authenticator,
    )

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit.Builder): AuthApiService = buildAuthApiService(retrofit)
}

