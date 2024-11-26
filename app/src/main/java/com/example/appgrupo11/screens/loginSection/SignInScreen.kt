package com.example.appgrupo11.screens.loginSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appgrupo11.auth.AuthViewModel
import com.example.appgrupo11.R
import com.example.appgrupo11.composables.PrimaryButton

@Composable
fun SignInScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: (String) -> Unit,
    onNavigateToSignUp: () -> Unit
) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) } // Estado para visibilidad de la contraseña


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

            Image(
                painter = painterResource(id = R.drawable.carrotcolored),
                contentDescription = "Logo de la App",
                modifier = Modifier
                    .height(55.dp)
                    .width(47.dp)
            )
            Spacer(modifier = Modifier.height(56.dp))


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


            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    errorContainerColor = Color(0xFFFFE6E6),
                )
            )
            Spacer(modifier = Modifier.height(16.dp))


            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    Icon(
                        imageVector = icon,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier
                            .clickable { passwordVisible = !passwordVisible }
                            .size(24.dp)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    errorContainerColor = Color(0xFFFFE6E6),
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de error si existe
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = {  }) {
                    Text(text = "Forgot Password?", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            if (isLoading) {
                CircularProgressIndicator()
            } else {
                PrimaryButton(
                    text = "Log In",
                    onClick = {
                        isLoading = true
                        errorMessage = ""

                        authViewModel.login(
                            username = email,
                            password = password,
                            onSuccess = { token ->
                                isLoading = false
                                onLoginSuccess(token)
                            },
                            onError = { error ->
                                isLoading = false
                                errorMessage = error
                            }
                        )
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToSignUp) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Don’t have an account? ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("Sign up")
                        }
                    }
                )
            }
        }
    }
}
