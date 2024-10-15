package com.example.appgrupo11.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class NavigationItem(
    val label: String,
    val title: String,
    val route: String,
    val icon: @Composable (Color) -> Unit,
)
