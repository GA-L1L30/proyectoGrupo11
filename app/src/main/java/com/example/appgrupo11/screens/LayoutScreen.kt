package com.example.appgrupo11.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.appgrupo11.composables.CustomNavigationBar
import com.example.appgrupo11.composables.CustomTopBar
import com.example.appgrupo11.data.getNavigationList

@Composable
fun LayoutScreen(
    selectedNavigationItemIndex: Int,
    onNavigationItemSelected: (Int) -> Unit,
    childrenComposable: @Composable () -> Unit,
    navController: NavHostController,
) {
    Scaffold(
        topBar = {
            CustomTopBar(title = getNavigationList()[selectedNavigationItemIndex].title, navController = navController)
        },
        bottomBar = {
            CustomNavigationBar(
                selectedNavigationItem = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                navController = navController
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
        ) {
            childrenComposable()
        }
    }
}