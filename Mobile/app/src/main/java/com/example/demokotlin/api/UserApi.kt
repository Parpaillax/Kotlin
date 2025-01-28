package com.example.demokotlin.api

import com.example.demokotlin.auth.viewmodel.Credential
import com.example.demokotlin.auth.viewmodel.User
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("user/login")
    suspend fun login(@Body user: Credential): String

    @POST("user/signup")
    suspend fun signup(@Body user: User)
}