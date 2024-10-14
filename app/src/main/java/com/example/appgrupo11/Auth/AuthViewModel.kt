package com.example.appgrupo11.Auth

// AuthViewModel.kt
import androidx.lifecycle.ViewModel

import com.example.appgrupo11.Retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AuthViewModel : ViewModel() {

    fun login(username: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val request = AuthRequest(username, password)

        RetrofitInstance.api.login(request).enqueue(object : Callback<AuthResponse> {
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
