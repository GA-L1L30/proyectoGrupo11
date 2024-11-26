package com.example.appgrupo11.navigation

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
    onNavigationItemSelected: (Int) -> Unit,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        if (!hasSeenOnboarding) {
            navController.navigate("onboarding")
        } else if (!isUserLoggedIn) {
            navController.navigate("signin")
        } else {
            navController.navigate("home")
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
                        popUpTo("signin") { inclusive = true }
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
                        popUpTo("signup") { inclusive = true }
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
                    HomeScreen(navController = navController, isDarkMode = isDarkMode)
                },
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
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
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("cart") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    CartScreen(navController = navController, isDarkMode)
                },
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("favorites") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    FavoritesScreen(navController = navController, isDarkMode)
                },
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("account") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    AccountScreen(
                        isDarkMode = isDarkMode,
                        onToggleDarkMode = onToggleDarkMode
                    )
                },
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("productDetail") {
            ProductDetailScreen(
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("orderAccepted") {
            OfferAcceptedScreen(
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("productsFiltered") {
            LayoutScreen(
                selectedNavigationItemIndex = selectedNavigationItemIndex,
                onNavigationItemSelected = onNavigationItemSelected,
                childrenComposable = {
                    SearchScreen(navController = navController, isDarkMode = isDarkMode)
                },
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )
        }
        composable("productsForCategory") {
            CategoryScreen(
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
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
