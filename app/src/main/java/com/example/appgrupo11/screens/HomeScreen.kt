package com.example.appgrupo11.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.R

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text="Dhaka, Banassre",
            fontSize = 18.sp,
        )

        FreshVegetablesBanner()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp)
        ) {
            item{

                Items(text = "Exclusive Offer")
                ExclusiveOffer()

            }


            item{
                Items(text = "Best Selling")
                BestSelling()
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
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

        Row( modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ){
            SmallGreenCircle()
            Circle()
            Circle()
        }




    }
}

@Composable
fun Items(
    text : String
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 30.dp)
        )
        TextSeeAll()
    }

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun CostumCard(
    imageRes: Int,
    title : String,
    description: String,
    price: String,
    onClick: () -> Unit
){
        Card (
            modifier = Modifier
                .width(190.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(1.dp, Color(0xFFE0E0E0), RoundedCornerShape(16.dp))
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
                        text = price,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )

                    ButtonAdd(
                        onClick = onClick
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
        .size(60.dp)
        .clip(RoundedCornerShape(20.dp))
        .background(Color(0xFF53B175))
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

@Composable
fun ExclusiveOffer(){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ){

        item{
            CostumCard(
                imageRes = R.drawable.banana,
                title = "Organic bananas",
                description = "7pcs, Priceg",
                price ="$4.99",
                onClick = {}
            )
        }

        item{
            CostumCard(
                imageRes = R.drawable.apple,
                title = "Red Apple",
                description = "1kg, Priceg",
                price ="$4.99",
                onClick = {}
            )
        }

        item{
            CostumCard(
                imageRes = R.drawable.strawberry,
                title = "Strawberry",
                description = "7pcs, Priceg",
                price ="$6.99",
                onClick = {}
            )
        }

    }
}

@Composable
fun BestSelling() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {

        item {
            CostumCard(
                imageRes = R.drawable.pepper,
                title = "Bell Pepper Red",
                description = "7pcs, Priceg",
                price = "$4.99",
                onClick = {}
            )
        }

        item {
            CostumCard(
                imageRes = R.drawable.ginger,
                title = "Ginger",
                description = "1kg, Priceg",
                price = "$4.99",
                onClick = {}
            )
        }

        item {
            CostumCard(
                imageRes = R.drawable.strawberry,
                title = "Strawberry",
                description = "7pcs, Priceg",
                price = "$6.99",
                onClick = {}
            ) }
        }
    }

@Composable
fun TextSeeAll(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text="See all" ,
            color = Color(0xFF53B175),
            fontSize = 20.sp,
            modifier = Modifier
                .padding(end = 30.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun SmallGreenCircle(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .size(width = 20.dp, height = 8.dp)
        .clip(CircleShape)
        .background(Color(0xFF53B175))
    )
}

@Composable
fun Circle(modifier: Modifier = Modifier){
    Box(modifier = modifier
        .size(width = 8.dp, height = 8.dp)
        .clip(CircleShape)
        .background(Color.Gray)
    )
}

