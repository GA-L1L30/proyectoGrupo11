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

import com.example.appgrupo11.R

import com.example.appgrupo11.composables.PrimaryButton


@Composable
fun SignUpScreen(onSignUpSuccess: () -> Unit, onNavigateToSignIn: () -> Unit) {

    // Estados
    var username by remember { mutableStateOf("") }
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
            // Logo de la app
            Image(
                painter = painterResource(id = R.drawable.carrotcolored),
                contentDescription = "Logo de la App",
                modifier = Modifier
                    .height(55.dp)
                    .width(47.dp)
            )
            Spacer(modifier = Modifier.height(56.dp))

            // Título e instrucciones
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Sign Up",
                    fontSize = 24.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = "Enter your credentials to continue",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Campo de correo electrónico
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
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

            // Campo de contraseña
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

            // Botón de "Forgot Password" alineado a la derecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                TextButton(onClick = { /* Handle click here */ }) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append(" By continuing you agree ")
                            }
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append("to our ")
                            }
                            withStyle(style = SpanStyle(color = Color.Green)) {
                                append("Terms of Service\n ")
                            }
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append("and ")
                            }
                            withStyle(style = SpanStyle(color = Color.Green)) {
                                append("Privacy Policy.")
                            }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))


            if (isLoading) {
                CircularProgressIndicator()
            } else {
                PrimaryButton(
                    text = "Sign Up",
                    onClick = {
                        isLoading = true
                        errorMessage = ""
                        onSignUpSuccess()
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onNavigateToSignIn) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Already have an account? ")
                        }
                        withStyle(style = SpanStyle(color = Color.Green)) {
                            append("Sign In")
                        }
                    }
                )
            }
        }
    }
}
