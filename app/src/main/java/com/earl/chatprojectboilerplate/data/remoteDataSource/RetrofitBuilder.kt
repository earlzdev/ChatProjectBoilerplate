package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://plannerok.ru/"

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

//class AuthInterceptor(): Interceptor {
//
//    private fun refreshToken(): Response {
//        // make an API call to get new token
//        return if (response.isSuccessful) {
//            val token = response.body()?.token
//            saveTokenToLocalStorage(token)
//            val newRequest = request
//                .newBuilder()
//                .header("Authorization", "Bearer $token")
//                .build()
//            chain.proceed(newRequest)
//        } else {
//            chain.proceed(request)
//        }
//    }
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val token = getFromStorage()
//        val request = chain.request()
//        if (token.isNullOrEmpty()) {
//            val newRequest = request
//                .newBuilder()
//                .header("Authorization", "Bearer $token")
//                .build()
//            val response = chain.proceed(newRequest)
//            return if (response.code() == 401) {
//                refreshToken()
//            } else {
//                response
//            }
//        } else {
//            refreshToken()
//        }
//        return chain.proceed(request)
//    }
//}