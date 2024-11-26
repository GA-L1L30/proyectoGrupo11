package com.example.appgrupo11.Navigation

import LocationScreen

import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.runtime.*

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.appgrupo11.screens.*
import com.example.appgrupo11.screens.cart.CartScreen
import com.example.appgrupo11.screens.favorites.FavoritesScreen

import com.example.appgrupo11.screens.loginSection.SignInScreen
import com.example.appgrupo11.screens.loginSection.SignUpScreen

@Composable
fun NavHostSetup(
    selectedNavigationItemIndex: Int,
    innerPadding: PaddingValues,
    isUserLoggedIn: Boolean,
    hasSeenOnboarding: Boolean,
    onLoginSuccess: () -> Unit,
    onOnboardingComplete: () -> Unit,
    onNavigationItemSelected: (Int) -> Unit, // Añadir este parámetro para manejar la selección
    isDarkMode: Boolean,  // Recibir el parámetro de modo oscuro
    onToggleDarkMode: (Boolean) -> Unit // Función para alternar el modo oscuro
) {
    val navController = rememberNavController() // Controlador de navegación

    // Controla la navegación inicial según el estado
    LaunchedEffect(Unit) {
        if (!hasSeenOnboarding) {
            navController.navigate("onboarding") // Si no ha visto la pantalla de onboarding
        } else if (!isUserLoggedIn) {
            navController.navigate("signin") // Si no está autenticado
        } else {
            navController.navigate("home") // Pantalla principal
        }
    }

    NavHost(navController = navController, startDestination = "home") {
        composable("onboarding") {
            OnBoardingScreen(onGetStartedClick = {
                onOnboardingComplete()
                navController.navigate("signin")
            })
        }
        composable("signin") {
            SignInScreen(
                onLoginSuccess = {
                    onLoginSuccess()
                    navController.navigate("home") {
                        popUpTo("signin") { inclusive = true } // Remover SignIn de la pila de navegación
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate("signup")
                }
            )
        }
        composable("signup") {
            SignUpScreen(
                onSignUpSuccess = {
                    navController.navigate("location") {
                        popUpTo("signup") { inclusive = true } // Remover SignUp de la pila de navegación
                    }
                },
                onNavigateToSignIn = {
                    navController.navigate("signin")
                }
            )
        }
        composable("home") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    HomeScreen(navController = navController)
                },
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode // Pasar la función onToggleDarkMode
            )
        }
        composable("findProducts") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    FindProductsScreen(navController = navController)
                },
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode // Pasar la función onToggleDarkMode
            )
        }
        composable("cart") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    CartScreen(navController = navController)
                },
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode // Pasar la función onToggleDarkMode
            )
        }
        composable("favorites") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    FavoritesScreen(navController = navController)
                },
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode // Pasar la función onToggleDarkMode
            )
        }
        composable("account") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    AccountScreen(
                        isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                        onToggleDarkMode = onToggleDarkMode // Pasar la función onToggleDarkMode
                    )
                },
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode // Pasar la función onToggleDarkMode
            )
        }
        composable("productDetail") {
            ProductDetailScreen(
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("orderAccepted") {
            OfferAcceptedScreen(
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("productsFiltered") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    SearchScreen(navController = navController)
                },
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode // Pasar la función onToggleDarkMode
            )
        }
        composable("productsForCategory") {
            CategoryScreen(
                navController = navController,
                isDarkMode = isDarkMode, // Pasar el parámetro isDarkMode
                onToggleDarkMode = onToggleDarkMode // Pasar la función onToggleDarkMode)
            )
        }
        composable("location") {
            LocationScreen(
                onNavigateToHome = {
                    navController.navigate("home")
                }
            )
        }
    }
}
