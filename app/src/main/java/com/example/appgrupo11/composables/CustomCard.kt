package com.example.appgrupo11.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appgrupo11.ui.theme.AppColors

//Anteriormente este codigo estaba en HomeScreen y se copio y pego aca porque se usa en mas pantallas
@Composable
fun CustomCard(
    imageRes: Int,
    title: String,
    description: String,
    price: Double,
    navController: NavController
){
    Card (
        modifier = Modifier
            .width(190.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(1.dp, AppColors.LightGray, RoundedCornerShape(16.dp))
            .shadow(8.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ){
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Card Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = description,
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "$$price",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )

                ButtonAdd(
                    onClick = {navController.navigate("productDetail")}
                )
            }
        }
    }
}

@Composable
fun ButtonAdd(
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .size(55.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(AppColors.LightGreen)
        .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "+",
            fontSize = 30.sp,
            color = Color.White
        )
    }
}
