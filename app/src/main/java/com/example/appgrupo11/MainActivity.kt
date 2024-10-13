package com.example.appgrupo11

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.appgrupo11.composables.CustomNavigationBar
import com.example.appgrupo11.composables.CustomTopBar
import com.example.appgrupo11.data.getNavigationList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appgrupo11.screens.AccountScreen
import com.example.appgrupo11.screens.CategoryScreen
import com.example.appgrupo11.screens.FindProductsScreen
import com.example.appgrupo11.screens.HomeScreen
import com.example.appgrupo11.screens.OfferAcceptedScreen
import com.example.appgrupo11.screens.ProductDetailScreen
import com.example.appgrupo11.screens.SearchScreen
import com.example.appgrupo11.ui.theme.AppGrupo11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            AppGrupo11Theme {

                var selectedNavigationItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                Scaffold(
                    topBar = { CustomTopBar(title = getNavigationList()[selectedNavigationItemIndex].title) },
                    bottomBar = {
                        CustomNavigationBar(
                            selectedNavigationItem = selectedNavigationItemIndex,
                            onNavigationItemSelected = { selectedNavigationItemIndex = it })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .background(Color.White)) {
                        when (selectedNavigationItemIndex) {
                            0 -> HomeScreen()
                            1 -> FindProductsScreen()
                            2 -> SearchScreen()
                            3 -> CategoryScreen()
                            4 -> AccountScreen()

                        }
                    }
                }

                //ProductDetailScreen()
            }
        }
    }
}

