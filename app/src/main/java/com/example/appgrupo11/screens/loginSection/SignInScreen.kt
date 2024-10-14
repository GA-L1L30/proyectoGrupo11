package com.example.appgrupo11.screens.loginSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.Auth.AuthRequest
import com.example.appgrupo11.Auth.AuthResponse
import com.example.appgrupo11.R
import com.example.appgrupo11.Retrofit.RetrofitInstance
import com.example.appgrupo11.composables.PrimaryButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SignInScreen(
    onLoginSuccess: (String) -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    // Estados
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo de la app
            Image(
                painter = painterResource(id = R.drawable.carrotcolored),
                contentDescription = "Logo de la App",
                modifier = Modifier
                    .height(55.dp)
                    .width(47.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Título e instrucciones
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 24.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "Enter your email and password",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Campo de correo electrónico
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Campo de contraseña
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de error si existe
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botón de "Forgot Password" alineado a la derecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /* Acción para recuperar contraseña */ }) {
                    Text(text = "Forgot Password?")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de inicio de sesión o indicador de carga
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                PrimaryButton(
                    text = "Log In",
                    onClick = {
                        isLoading = true
                        errorMessage = ""

                        RetrofitInstance.api.login(
                            AuthRequest(username = email, password = password)
                        ).enqueue(object : Callback<AuthResponse> {
                            override fun onResponse(
                                call: Call<AuthResponse>,
                                response: Response<AuthResponse>
                            ) {
                                isLoading = false
                                if (response.isSuccessful) {
                                    val token = response.body()?.token
                                    onLoginSuccess(token ?: "")
                                } else {
                                    errorMessage = "Login failed. Please try again."
                                }
                            }

                            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                                isLoading = false
                                errorMessage = "Error: ${t.message}"
                            }
                        })
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToSignUp) {
                Text(text = "Don’t have an account? Sign up")
            }
        }
    }
}
