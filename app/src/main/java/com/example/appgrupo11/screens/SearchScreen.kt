package com.example.appgrupo11.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appgrupo11.composables.CustomCard
import com.example.appgrupo11.composables.Search

@Composable
fun SearchScreen(){
    val viewModel: SearchViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 100.dp, end = 20.dp, bottom = 10.dp) ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Search("Egg")

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(viewModel.searchProducts.value){
                product ->
                CustomCard(
                    imageRes = product.imageRes,
                    title = product.title,
                    description = product.description,
                    price = product.price,
                    onClick = {}
                )
            }
        }
    }
}