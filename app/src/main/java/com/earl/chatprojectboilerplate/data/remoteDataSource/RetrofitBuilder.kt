package com.earl.chatprojectboilerplate.data.remoteDataSource

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
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