package com.example.appgrupo11.auth

// AuthRequest.kt
data class AuthRequest(
    val username: String,
    val password: String
)

// AuthResponse.kt
data class AuthResponse(
    val token: String
)
