package com.example.appgrupo11.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appgrupo11.composables.CustomCard
import com.example.appgrupo11.composables.CustomTopBar

@Composable
fun CategoryScreen(
    navController: NavController,
    isDarkMode: Boolean,        // Agregar parámetro isDarkMode
    onToggleDarkMode: (Boolean) -> Unit // Agregar parámetro onToggleDarkMode
) {
    val categoryViewModel: CategoryViewModel = viewModel()

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Beverages",
                navigationIcon = {
                    Icon(
                        Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(if(isDarkMode) Color.Black else Color.White)
                .fillMaxSize()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp, bottom = 30.dp)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(categoryViewModel.products) { product ->
                    CustomCard(
                        imageUrl = product.imageUrl,
                        title = product.title,
                        description = product.description,
                        price = product.price,
                        navController = navController,
                        isDarkMode = isDarkMode
                    )
                }
            }
        }
    }
}
