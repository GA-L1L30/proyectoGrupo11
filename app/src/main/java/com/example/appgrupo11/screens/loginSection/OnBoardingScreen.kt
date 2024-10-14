package com.example.appgrupo11.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.R
import com.example.appgrupo11.composables.PrimaryButton

@Composable
fun OnBoardingScreen(onGetStartedClick: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(id = R.drawable.backgroundimage),
                contentDescription = "Contenido de fondo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(220.dp))
                Image(
                    painter = painterResource(id = R.drawable.carrotlogo),
                    contentDescription = "Logo de la App",
                    modifier = Modifier
                        .width(50.dp)
                        .height(56.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "Welcome\nto our store", modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily =  FontFamily.SansSerif,
                        fontSize = 48.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 59.sp,
                        color = Color.White
                    ))
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Ger your groceries in as fast as one hour",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontFamily = FontFamily.Serif,
                        fontSize = 16.sp,
                        lineHeight = 15.sp,
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(35.dp))
                PrimaryButton(text = "Get Started") {
                    onGetStartedClick()
                }
            }
        }
    }
}
