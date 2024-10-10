package com.example.appgrupo11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.appgrupo11.screens.CategoryScreen
import com.example.appgrupo11.screens.FindProductsScreen
import com.example.appgrupo11.screens.HomeScreen
import com.example.appgrupo11.ui.theme.AppGrupo11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGrupo11Theme {
                //VERIFICAR PROPPERTIES PARA EL FOOTER Y HEADER COMPARTIDOS
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //HomeScreen()
                    //FindProductsScreen()
                    CategoryScreen()
                }
            }
        }
    }
}

