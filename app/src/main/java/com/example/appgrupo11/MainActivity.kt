package com.example.appgrupo11

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.appgrupo11.composables.CustomNavigationBar
import com.example.appgrupo11.composables.CustomTopBar
import com.example.appgrupo11.data.getNavigationList
import com.example.appgrupo11.screens.HomeScreen
import com.example.appgrupo11.ui.theme.AppGrupo11Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppGrupo11Theme {

                var selectedNavigationItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }
                //VERIFICAR PROPPERTIES PARA EL FOOTER Y HEADER COMPARTIDOS
                Scaffold(
                    topBar = { CustomTopBar(title = getNavigationList()[selectedNavigationItemIndex].title) },
                    bottomBar = { CustomNavigationBar(selectedNavigationItem = selectedNavigationItemIndex, onNavigationItemSelected = {selectedNavigationItemIndex = it}) },
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Log.d(innerPadding.toString(), "innerPadding")
                    HomeScreen()
                }
            }
        }
    }
}

