package com.example.appgrupo11.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: AuthRequest): Call<AuthResponse>
}
