package com.example.appgrupo11.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appgrupo11.R
import com.example.appgrupo11.composables.GrayButton
import com.example.appgrupo11.composables.PrimaryButton
import com.example.appgrupo11.ui.theme.AppColors

@Composable
fun OfferAcceptedScreen(
    navController: NavController,
    isDarkMode: Boolean,  // Recibir el parámetro de modo oscuro
    onToggleDarkMode: (Boolean) -> Unit // Función para alternar el modo oscuro
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(40.dp))


            Image(
                painter = painterResource(id = R.drawable.tick),
                contentDescription="Tick",
                modifier = Modifier
                    .size(230.dp),
            )

        Text(
            text= "Your Order has been accepted",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 25.dp, start = 10.dp , end = 10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Text(
            text= "Your items has been placed and is on it's way to being processed",
            fontSize = 15.sp,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 120.dp, start = 40.dp , end = 40.dp)
                .fillMaxWidth(),
            color = Color.Gray,
            textAlign = TextAlign.Center
        )

        PrimaryButton(text = "Track Order"){}

        Spacer(modifier = Modifier.height(10.dp))

        GrayButton(text = "Back to home", leftIcon = {}, onClick = {navController.navigate("home")}, textColor = Color.Black)
    }
}