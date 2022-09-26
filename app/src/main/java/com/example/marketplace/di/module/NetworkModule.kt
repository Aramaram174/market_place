package com.example.marketplace.di.module

import android.app.Application
import android.content.Context
import com.example.marketplace.data.api.requests.ApiService
import com.example.marketplace.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import com.google.gson.GsonBuilder

val appModule = module {
    single { provideOkHttpClient() }
    single {
        provideRetrofit(
            get(),
//            BASE_URL = "https://www.marketplace.com/"
//            BASE_URL = "https://darkside.megafeed.com/"
            BASE_URL = "https://1de0-91-103-58-71.eu.ngrok.io"
//            BASE_URL = "http://192.168.5.5:8000/" //home
//            BASE_URL = "http://10.27.140.207:8000/" //work
        )//Todo(move to resources)
    }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
//    factory { Glide.with(androidContext()) }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(Interceptor { chain ->
            val ongoing = chain.request().newBuilder()
            ongoing.addHeader("Accept", "application/json")
            chain.proceed(ongoing.build())
        })
        .build()
} else OkHttpClient
    .Builder()
    .build()

//var httpClient: OkHttpClient = Builder()
//    .addInterceptor(Interceptor { chain ->
//        val ongoing: Builder = chain.request().newBuilder()
//        ongoing.addHeader("Accept", "application/json;versions=1")
//        if (isUserLoggedIn()) {
//            ongoing.addHeader("Authorization", getToken())
//        }
//        chain.proceed(ongoing.build())
//    }).build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

