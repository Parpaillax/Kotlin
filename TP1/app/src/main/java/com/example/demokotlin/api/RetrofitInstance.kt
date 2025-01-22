package com.example.demokotlin.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://raw.githubusercontent.com/Chocolaterie/EniWebService/main/api/" // Remplace par l'URL de ton API

    // Configure Moshi
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // Support pour les classes Kotlin
        .build()

    // Configure Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
