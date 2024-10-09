package com.example.appgrupo11.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.R

@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        contentAlignment = Alignment.TopCenter
    ){
        Text(
            text="Dhaka, Banassre",
            fontSize = 18.sp
        )

        FreshVegetablesBanner()

        ExclusiveOffer()

    }
}

@Composable
fun FreshVegetablesBanner(){
    Box(
        modifier = Modifier
            .padding(top = 40.dp)
            .size(width = 340.dp, height = 110.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFEEDEDB).copy(alpha = 0.8f),
                    Color.White,
                    Color(0xFFEEDEDB).copy(alpha = 0.8f)
                )

                )
            )

    ) {
        Image(
            painterResource(id = R.drawable.vegetables),
            contentDescription = "vegetables",
            modifier = Modifier
                .size(145.dp)
                .align(Alignment.CenterStart)
                .padding(end = 19.dp)
        )

        Image(
            painterResource(id = R.drawable.vegetable1),
            contentDescription = "vegetables",
            modifier = Modifier
                .size(45.dp)
                .align(Alignment.BottomEnd)
        )


        Image(
            painterResource(id = R.drawable.vegetable2),
            contentDescription = "vegetables",
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopStart)
        )

        /*Image(
            painterResource(id = R.drawable.vegetable3),
            contentDescription = "vegetables",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopEnd)
        )*/
        //No queda ubicado en el topEnd y se superpone con fresh Vegetables

        Image(
            painterResource(id = R.drawable.freshvegetables),
            contentDescription = "fresh vegetables",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterEnd)
                .padding(end = 30.dp)
        )
    }
}

@Composable
fun ExclusiveOffer(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 180.dp),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text="Exclusive Offer",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp)
        )
    }
}



