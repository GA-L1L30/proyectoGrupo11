package com.example.appgrupo11.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.appgrupo11.ui.theme.LightGray

@Composable
fun DefaultNavigationIcon(onClick: () -> Unit = {}) {
    IconButton(onClick = onClick) {
        Icon(Icons.Filled.Menu, contentDescription = "Back")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    title: String,
    containerColor: Color = LightGray,
    navigationIcon: @Composable () -> Unit = { DefaultNavigationIcon() },
    navController: NavController,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val appBarColor = if (isDarkMode) {
        Color.Black
    } else {
        containerColor
    }

    val textColor = if (isDarkMode) {
        Color.White
    } else {
        Color.Black
    }

    CenterAlignedTopAppBar(
        title = {
            Box(contentAlignment = Alignment.Center) {
                Text(text = title, color = textColor)
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("home") }) {
                navigationIcon()
            }
        },
        colors = topAppBarColors(
            containerColor = appBarColor,
        ),
    )
}
