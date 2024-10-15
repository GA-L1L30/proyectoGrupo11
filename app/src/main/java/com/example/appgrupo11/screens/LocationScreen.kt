package com.example.appgrupo11.screens.loginSection

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
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
fun LocationScreen(onNavigateToHome: () -> Unit) {

    var selectedZone by remember { mutableStateOf("Select your zone") }
    var expandedZone by remember { mutableStateOf(false) }

    var selectedArea by remember { mutableStateOf("Select your area") }
    var expandedArea by remember { mutableStateOf(false) }

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
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Logo de la App",
                modifier = Modifier
                    .height(170.dp)
                    .width(225.dp)
            )
            Spacer(modifier = Modifier.height(56.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Select Your Location",
                    fontSize = 24.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge
                )

                Text(
                    text = "Switch on your location to stay in tune with\n" +
                            "what’s happening in your area",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            // Elemento que activa el Dropdown para zonas
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable { expandedZone = true }
                .padding(16.dp)) {
                Text(text = selectedZone, style = MaterialTheme.typography.bodyLarge)
            }

            // Dropdown para zonas
            DropdownMenu(
                expanded = expandedZone,
                onDismissRequest = { expandedZone = false }
            ) {

                listOf("Banasree", "Zone 2", "Zone 3").forEach { zone ->

                    DropdownMenuItem(text = {
                        Text(text = zone)
                    },
                        onClick = {
                            expandedZone = false

                        })
                }
            }



            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = "Submit",
                onClick = {
                    onNavigateToHome()
                }
            )
        }
    }
}
