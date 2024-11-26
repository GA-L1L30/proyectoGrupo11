package com.example.appgrupo11.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.appgrupo11.data.Product
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appgrupo11.composables.CustomCard
import com.example.appgrupo11.ui.theme.AppColors

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(), navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dhaka, Banassre",
            fontSize = 18.sp,
        )

        FreshVegetablesBanner()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp)
        ) {
            when (uiState) {
                is HomeUiState.Loading -> {
                    item { Text("Loading...") }
                }

                is HomeUiState.Success -> {
                    val successState = uiState as HomeUiState.Success

                    item {
                        Items(text = "Exclusive Offer")
                        ExclusiveOffer(products = successState.exclusiveOffers, navController)

                    }

                    item {
                        Items(text = "Best Selling")
                        BestSelling(products = successState.bestSelling, navController)
                    }
                }

                else -> {
                    item{
                        Text("Error with data")
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}


@Composable
fun FreshVegetablesBanner(){
    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .size(width = 340.dp, height = 110.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        AppColors.Beige.copy(alpha = 0.8f),
                        Color.White,
                        AppColors.Beige.copy(alpha = 0.8f)
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
fun ExclusiveOffer(products: List<Product>, navController: NavController){
    Log.d("testGonza", navController.toString())
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ){
        items(products.size){ i->
            val product = products[i]
            CustomCard(
               imageUrl = product.imageUrl,
                title =  product.title,
                description = product.description,
                price = product.price,
                navController
            )
        }
    }
}

@Composable
fun BestSelling(products: List<Product>, navController: NavController) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        items(products.size) { i ->
            val product = products[i]
            CustomCard(
                imageUrl = product.imageUrl,
                title = product.title,
                description = product.description,
                price = product.price,
                navController = navController
            )
        }
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
            fontSize = 16.sp,
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
        .background(AppColors.LightGreen)
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


