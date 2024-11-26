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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appgrupo11.composables.CustomCard
import com.example.appgrupo11.composables.Search

@Composable
fun SearchScreen(navController: NavHostController, isDarkMode: Boolean) {
    val viewModel: SearchViewModel = viewModel()
    val searchQuery by viewModel.searchQuery
    val searchProducts by viewModel.searchProducts.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, bottom = 10.dp) ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Search(
            query = searchQuery,
            onQueryChange = { query -> viewModel.updateSearchQuery(query) },
            placeholderText = "Search Store",
            onTrailingIconClick = { }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(searchProducts){
                product ->
                CustomCard(
                    imageUrl = product.imageUrl,
                    title = product.title,
                    description = product.description,
                    price = product.price,
                    navController = navController,
                     isDarkMode,
                )
            }
        }
    }
}

