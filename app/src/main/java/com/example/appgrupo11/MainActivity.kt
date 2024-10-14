package com.example.appgrupo11

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.appgrupo11.composables.CustomNavigationBar
import com.example.appgrupo11.composables.CustomTopBar
import com.example.appgrupo11.data.getNavigationList
import com.example.appgrupo11.screens.AccountScreen
import com.example.appgrupo11.screens.FindProductsScreen
import com.example.appgrupo11.screens.HomeScreen
import com.example.appgrupo11.screens.OfferAcceptedScreen
import com.example.appgrupo11.screens.SplashScreen
import com.example.appgrupo11.screens.cart.CartScreen
import com.example.appgrupo11.screens.favorites.FavoritesScreen
import com.example.appgrupo11.ui.theme.AppGrupo11Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGrupo11Theme {
                var showSplashScreen by remember { mutableStateOf(true) }
                var selectedNavigationItemIndex by rememberSaveable { mutableIntStateOf(0) }


                LaunchedEffect(Unit) {
                    delay(2000)
                    showSplashScreen = false
                }

                if (showSplashScreen) {
                    SplashScreen()  // Pantalla de bienvenida
                } else {
                    MainContent(selectedNavigationItemIndex) { newIndex ->
                        selectedNavigationItemIndex = newIndex  // Actualiza la navegación
                    }
                }
            }
        }
    }

    @Composable
    fun MainContent(
        selectedNavigationItemIndex: Int,
        onNavigationItemSelected: (Int) -> Unit
    ) {
        Scaffold(
            topBar = {
                CustomTopBar(title = getNavigationList()[selectedNavigationItemIndex].title)
            },
            bottomBar = {
                CustomNavigationBar(
                    selectedNavigationItem = selectedNavigationItemIndex,
                    onNavigationItemSelected = onNavigationItemSelected
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding).background(Color.White)) {
                when (selectedNavigationItemIndex) {
                   0 -> HomeScreen()
                    1 -> FindProductsScreen()
                    //2 -> OfferAcceptedScreen()
                    2 -> CartScreen()
                   3 -> FavoritesScreen()
                    4 -> AccountScreen()
                }
            }
        }
    }

}