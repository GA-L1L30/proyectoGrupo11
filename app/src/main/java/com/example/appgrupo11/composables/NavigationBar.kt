package com.example.appgrupo11.composables

import android.util.Log
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appgrupo11.data.NavigationItem
import com.example.appgrupo11.data.getNavigationList
import com.example.appgrupo11.ui.theme.AppColors


@Composable
fun CustomNavigationBar(selectedNavigationItem: Int,onNavigationItemSelected: (Int) -> Unit, navigationList: List<NavigationItem> = getNavigationList(), navController: NavController, isDarkMode: Boolean) {
    NavigationBar(containerColor = if(isDarkMode) AppColors.DarkViolet else Color.White ,modifier = Modifier.graphicsLayer {
        clip = true
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        shadowElevation = 20f
    }) {
        navigationList.forEachIndexed { index, item ->
            NavigationBarItem(
                label = { Text(text = item.label, color = if(selectedNavigationItem == index) AppColors.LightGreen else if(isDarkMode) Color.White else Color.Black) },
                icon = { item.icon(if (selectedNavigationItem == index) AppColors.LightGreen else if (isDarkMode) Color.White else Color.Black) }
                ,
                onClick = {
                    onNavigationItemSelected(index)
                    navController.navigate(item.route)
                },
                selected = false

            )
        }
    }
}