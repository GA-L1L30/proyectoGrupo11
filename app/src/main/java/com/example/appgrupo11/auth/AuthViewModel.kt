package com.example.appgrupo11.auth

// AuthViewModel.kt
import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun login(username: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val request = AuthRequest(username, password)

        apiService.login(request).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    response.body()?.token?.let { token ->
                        onSuccess(token)
                    } ?: onError("Token is null")
                } else {
                    onError("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                onError(t.message ?: "Unknown error")
            }
        })
    }
}
