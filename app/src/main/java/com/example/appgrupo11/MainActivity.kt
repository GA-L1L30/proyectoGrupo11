package com.example.appgrupo11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.appgrupo11.composables.CustomNavigationBar
import com.example.appgrupo11.composables.CustomTopBar
import com.example.appgrupo11.data.getNavigationList
import com.example.appgrupo11.screens.*
import com.example.appgrupo11.screens.cart.CartScreen
import com.example.appgrupo11.screens.favorites.FavoritesScreen
import com.example.appgrupo11.screens.loginSection.SignInScreen
import com.example.appgrupo11.ui.theme.AppGrupo11Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGrupo11Theme {
                var showSplashScreen by remember { mutableStateOf(true) }
                var isUserLoggedIn by rememberSaveable { mutableStateOf(false) }
                var selectedNavigationItemIndex by rememberSaveable { mutableIntStateOf(0) }

                // Simula la pantalla de bienvenida durante 2 segundos
                LaunchedEffect(Unit) {
                    delay(2000)
                    showSplashScreen = false
                }

                // Controla la navegación según el estado de autenticación
                when {
                    showSplashScreen -> SplashScreen() // Pantalla de bienvenida

                    !isUserLoggedIn -> SignInScreen( // Redirige al login si no está autenticado
                        onLoginSuccess = { token ->
                            isUserLoggedIn = true // Actualiza el estado de autenticación
                            // Aquí puedes almacenar el token si es necesario.
                        },
                        onNavigateToSignUp = {
                            // Navega a la pantalla de registro (implementa si es necesario)
                        }
                    )

                    else -> MainContent(
                        selectedNavigationItemIndex = selectedNavigationItemIndex,
                        onNavigationItemSelected = { newIndex ->
                            selectedNavigationItemIndex = newIndex
                        }
                    )
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
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(Color.White)
            ) {
                when (selectedNavigationItemIndex) {
                    0 -> HomeScreen()
                    1 -> FindProductsScreen()
                    2 -> CartScreen()
                    3 -> FavoritesScreen()
                    4 -> AccountScreen()
                }
            }
        }
    }
}
