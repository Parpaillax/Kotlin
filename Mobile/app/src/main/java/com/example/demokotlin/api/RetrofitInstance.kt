package com.example.demokotlin.api

import com.example.demokotlin.auth.viewmodel.SettingsDataStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://10.102.200.22:3000/api/" // Remplace par l'URL de ton API
//    private lateinit var dataStoreManager: SettingsDataStore
    // Configure Moshi
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // Support pour les classes Kotlin
        .build()

    // Configure Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

//    val clientWithAuth = OkHttpClient.Builder().addInterceptor { chain ->
//        val token = runBlocking { dataStoreManager.token.firstOrNull() }
//        val request = chain.request().newBuilder()
//            .addHeader("Authorization", "Bearer $token")
//            .build()
//        chain.proceed(request)
//    }.build()
//
//    val retrofitWithAuth = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .client(clientWithAuth)
//        .build()
}
