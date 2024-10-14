package com.example.appgrupo11.screens.loginSection

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit
) {
    Column {
        Button(onClick = onSignUpSuccess) {
            Text("Registrarse")
        }
    }
}

