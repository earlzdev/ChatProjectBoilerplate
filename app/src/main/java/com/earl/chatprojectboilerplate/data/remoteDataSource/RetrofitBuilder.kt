package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.earl.chatprojectboilerplate.data.remoteDataSource.models.AccessTokensDto
import com.earl.chatprojectboilerplate.domain.models.TokenManager
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

private const val baseUrl = "https://plannerok.ru/"

fun provideAuthenticator(tokenManager: TokenManager): Authenticator = AuthAuthenticator(tokenManager)

fun provideAuthenticateInterceptor(tokenManager: TokenManager): AuthInterceptor = AuthInterceptor(tokenManager)

fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

fun provideGsonConverter() = GsonBuilder()
    .setLenient()
    .create()

fun provideOkHttpClient(
    authInterceptor: AuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    authenticator: Authenticator,
) = OkHttpClient.Builder()
    .addInterceptor(authInterceptor)
    .addInterceptor(loggingInterceptor)
    .authenticator(authenticator)
    .build()


fun provideRetrofitBuilder(): Retrofit.Builder =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())

fun buildAuthApiService(retrofit: Retrofit.Builder): AuthApiService = retrofit
    .build()
    .create(AuthApiService::class.java)

fun buildNetworkService() : NetworkService {
    val interceptor = HttpLoggingInterceptor()
    val gsonConverter = GsonBuilder()
        .setLenient()
        .create()
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
    return Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gsonConverter))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(baseUrl)
        .build()
        .create(NetworkService::class.java)
}

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getAccessToken().first()
        }
        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
): Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val token = runBlocking {
            tokenManager.getAccessToken().first()
        }
        return runBlocking {
            val newToken = getNewToken(token)

            /**
             * Couldn't refresh the token, so restart the login process
             */
            if (!newToken.isSuccessful || newToken.body() == null) {
                tokenManager.deleteAccessToken()
            }

            newToken.body()?.let {
                tokenManager.saveAccessToken(it.accessToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String?): retrofit2.Response<AccessTokensDto> {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jwt-test-api.onrender.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        val service = retrofit.create(AuthApiService::class.java)
        return service.refreshToken("Bearer $refreshToken")
    }
}