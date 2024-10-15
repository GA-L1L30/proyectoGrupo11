package com.example.appgrupo11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("signIn") {
                        SignInScreen(
                            onLoginSuccess = { token ->
                                isUserLoggedIn = true
                                navController.navigate("home")
                            },
                            onNavigateToSignUp = {
                                navController.navigate("signUp")
                            }
                        )
                    }
                    composable("home") {
                        LayoutScreen(
                            selectedNavigationItemIndex = selectedNavigationItemIndex,
                            onNavigationItemSelected = { index ->
                                selectedNavigationItemIndex = index
                            },
                            childrenComposable = {
                                HomeScreen(navController = navController)
                            },
                            navController = navController,
                        )
                    }
                    composable("findProducts") {
                        LayoutScreen(
                            selectedNavigationItemIndex = selectedNavigationItemIndex,
                            onNavigationItemSelected = { index ->
                                selectedNavigationItemIndex = index
                            },
                            childrenComposable = {
                                FindProductsScreen()
                            },
                            navController = navController,
                        )
                    }
                    composable("cart") {
                        LayoutScreen(
                            selectedNavigationItemIndex = selectedNavigationItemIndex,
                            onNavigationItemSelected = { index ->
                                selectedNavigationItemIndex = index
                            },
                            childrenComposable = {
                                CartScreen(navController = navController)
                            },
                            navController = navController,
                        )
                    }
                    composable("favorites") {
                        LayoutScreen(
                            selectedNavigationItemIndex = selectedNavigationItemIndex,
                            onNavigationItemSelected = { index ->
                                selectedNavigationItemIndex = index
                            },
                            childrenComposable = {
                                FavoritesScreen(navController = navController)
                            },
                            navController = navController,
                        )

                    }
                    composable("account") {
                        LayoutScreen(
                            selectedNavigationItemIndex = selectedNavigationItemIndex,
                            onNavigationItemSelected = { index ->
                                selectedNavigationItemIndex = index
                            },
                            childrenComposable = {
                                AccountScreen()
                            },
                            navController = navController,
                        )
                    }
                    composable("productDetail") {
                        ProductDetailScreen(navController = navController)
                    }
                    composable("orderAccepted") {
                        OfferAcceptedScreen(navController = navController)
                    }
                }

                // Simula la pantalla de bienvenida durante 2 segundos
                LaunchedEffect(Unit) {
                    delay(2000)
                    showSplashScreen = false
                }
            }
        }
    }
}
