package com.earl.chatprojectboilerplate.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.earl.chatprojectboilerplate.data.remoteDataSource.*
import com.earl.chatprojectboilerplate.domain.models.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
    fun provideAuthAuthenticator(tokenManager: TokenManager) = provideAuthenticator(tokenManager)

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager) = provideAuthenticateInterceptor(tokenManager)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder = provideRetrofitBuilder()

    @Singleton
    @Provides
    fun provideHttpClient(
        authInterceptor: AuthInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        authenticator: okhttp3.Authenticator,
    ) = provideOkHttpClient(
        authInterceptor,
        loggingInterceptor,
        authenticator
    )

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit.Builder) = buildAuthApiService(retrofit)

}

