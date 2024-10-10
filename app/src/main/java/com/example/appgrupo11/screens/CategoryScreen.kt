package com.example.appgrupo11.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appgrupo11.composables.CustomCard

@Composable
fun CategoryScreen(){
    val categoryViewModel : CategoryViewModel = viewModel()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp, bottom = 30.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(categoryViewModel.products){
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
