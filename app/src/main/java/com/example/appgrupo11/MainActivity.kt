package com.example.appgrupo11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.appgrupo11.composables.CustomNavigationBar
import com.example.appgrupo11.composables.CustomTopBar
import com.example.appgrupo11.data.getNavigationList
import com.example.appgrupo11.screens.*
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
                var showOnBoarding by rememberSaveable { mutableStateOf(true) }
                var isLoggedIn by rememberSaveable { mutableStateOf(false) }
                var selectedNavigationItemIndex by rememberSaveable { mutableIntStateOf(0) }

                LaunchedEffect(Unit) {
                    delay(2000) // SplashScreen por 2 segundos
                    showSplashScreen = false
                }

                when {
                    showSplashScreen -> SplashScreen()
                    showOnBoarding -> OnBoardingScreen {
                        showOnBoarding = false
                    }
                    !isLoggedIn -> SignInScreen(
                        onLoginSuccess = { isLoggedIn = true },
                        onNavigateToSignUp = { isLoggedIn = true } // Simulamos registro exitoso
                    )
                    else -> MainContent(selectedNavigationItemIndex) { newIndex ->
                        selectedNavigationItemIndex = newIndex
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
            when (selectedNavigationItemIndex) {
                0 -> HomeScreen()
                1 -> FindProductsScreen()
                2 -> OfferAcceptedScreen()
                else -> HomeScreen()
            }
        }
    }
}
