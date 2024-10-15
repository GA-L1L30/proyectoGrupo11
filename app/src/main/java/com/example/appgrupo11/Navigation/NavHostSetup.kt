package com.example.appgrupo11.Navigation

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appgrupo11.screens.HomeScreen
import com.example.appgrupo11.screens.OnBoardingScreen

import com.example.appgrupo11.screens.loginSection.SignInScreen
import com.example.appgrupo11.screens.loginSection.SignUpScreen

@Composable
fun NavHostSetup() {
    val navController = rememberNavController()  // Controlador de navegación

    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") {
            OnBoardingScreen(onGetStartedClick = {
                navController.navigate("signin") // Navega a la pantalla de inicio de sesión
            })
        }
        composable("signin") {
            SignInScreen(
                onLoginSuccess = {
                    // Aquí puedes manejar la lógica para el inicio de sesión exitoso
                    // Por ejemplo, navegar a la pantalla principal
                    navController.navigate("home") // Suponiendo que "home" es tu pantalla principal
                },
                onNavigateToSignUp = {
                    // Navegar a la pantalla de registro
                    navController.navigate("signup") // Asegúrate de tener una pantalla de registro
                }
            )
        }
        // Asegúrate de añadir una entrada para la pantalla de registro
        composable("signup") {
            SignUpScreen (
                onSignUpSuccess = {
                    navController.navigate("home")
                }
            )
        }
    }
}