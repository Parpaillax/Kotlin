package com.example.demokotlin.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://10.102.200.22:3000/api/" // Remplace par l'URL de ton API
    val client = OkHttpClient.Builder().build()

    // Configure Moshi
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // Support pour les classes Kotlin
        .build()

    // Configure Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .build()
}
