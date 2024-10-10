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
import com.example.appgrupo11.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(title: String) {
    CenterAlignedTopAppBar(
        title = { Box(contentAlignment = Alignment.Center) {
            Text(text = title)
        } },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Back")
            }
        },
        colors = topAppBarColors(
            containerColor = LightGray,
        ),

    )
}