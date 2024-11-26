package com.example.appgrupo11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import com.example.appgrupo11.navigation.NavHostSetup
import com.example.appgrupo11.screens.*
import com.example.appgrupo11.ui.theme.AppGrupo11Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkTheme by rememberSaveable { mutableStateOf(false) } // Agregar control del tema oscuro

            AppGrupo11Theme(darkTheme = darkTheme) {
                var showSplashScreen by remember { mutableStateOf(true) }
                var isUserLoggedIn by remember { mutableStateOf(false) }
                var hasSeenOnboarding by remember { mutableStateOf(false) }
                var selectedNavigationItemIndex by remember { mutableStateOf(0) }

                // Simula la pantalla de bienvenida durante 2 segundos
                LaunchedEffect(Unit) {
                    delay(2000)
                    showSplashScreen = false
                }

                // Controla la navegación inicial
                if (showSplashScreen) {
                    SplashScreen() // Pantalla de bienvenida
                } else {
                    // Aquí se hace el control de la navegación, fuera del Scaffold
                    NavHostSetup(
                        selectedNavigationItemIndex = selectedNavigationItemIndex,
                        innerPadding = PaddingValues(0.dp), // Cambia esto según sea necesario
                        isUserLoggedIn = isUserLoggedIn,
                        hasSeenOnboarding = hasSeenOnboarding,
                        onLoginSuccess = { isUserLoggedIn = true },
                        onOnboardingComplete = { hasSeenOnboarding = true },
                        onNavigationItemSelected = { newIndex -> selectedNavigationItemIndex = newIndex },
                        isDarkMode = darkTheme,  // Pasar el estado de darkTheme
                        onToggleDarkMode = { darkTheme = !darkTheme } // Cambiar el estado al hacer clic
                    )
                }
            }
        }
    }
}

