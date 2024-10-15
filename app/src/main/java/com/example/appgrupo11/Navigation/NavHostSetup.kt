package com.example.appgrupo11.Navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appgrupo11.composables.CustomNavigationBar
import com.example.appgrupo11.composables.CustomTopBar
import com.example.appgrupo11.data.getNavigationList
import com.example.appgrupo11.screens.*
import com.example.appgrupo11.screens.cart.CartScreen
import com.example.appgrupo11.screens.favorites.FavoritesScreen
import com.example.appgrupo11.screens.loginSection.LocationScreen
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
    onNavigationItemSelected: (Int) -> Unit // Añadir este parámetro para manejar la selección
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
            )

        }
        composable("account") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
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
        composable("productsFiltered") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    SearchScreen(navController = navController)
                },
                navController = navController,
            )

        }
        composable("location") {
            LocationScreen(
                onNavigateToHome ={
                    navController.navigate("home")
                }
            )
        }
    }
}

