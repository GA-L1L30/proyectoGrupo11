package com.example.appgrupo11.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.appgrupo11.data.NavigationItem
import com.example.appgrupo11.data.getNavigationList


@Composable
fun CustomNavigationBar(selectedNavigationItem: Int, onNavigationItemSelected: (Int) -> Unit, navigationList: List<NavigationItem> = getNavigationList()) {

    NavigationBar(containerColor = Color.White, modifier = Modifier.graphicsLayer {
        clip = true
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        shadowElevation = 20f
    }) {
        navigationList.forEachIndexed { index, item ->
            NavigationBarItem(
                label = { Text(text = item.label, color = if(selectedNavigationItem == index) Color(0xFF53B175) else Color.Black) },
                icon = { item.icon(if (selectedNavigationItem == index) Color(0xFF53B175) else Color.Black) } // Cambia el color según la selección
                ,
                onClick = {
                    onNavigationItemSelected(index)
                },
                selected = false

            )
        }
    }
}