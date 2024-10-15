package com.example.appgrupo11.screens


import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.composables.PrimaryButton
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersPopUp(onDismiss: () -> Unit, navController: NavController){
    val titles = listOf("Eggs", "Noodles & Pasta", "Chips & Crips", "Fast Food")
    val titles2 = listOf("Individual Callection", "CocaCola", "Ifad", "Kazi Farmas")

    val selectedTitles = remember { mutableStateListOf<Boolean>().apply { repeat(titles.size) { add(false) } } }
    val selectedTitles2 = remember { mutableStateListOf<Boolean>().apply { repeat(titles.size) { add(false) } } }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                }
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Filters", fontWeight = FontWeight.Bold)
                }
            }
            },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, top = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                    Text(
                        text = "Categories",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        items(titles.size) { index ->
                            Item(
                                title = titles[index],
                                isSelected = selectedTitles[index],
                                onCheckedChange = { selectedTitles[index] = it }
                            )
                        }
                    }

                    Text(
                        text = "Brand",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        items(titles2.size) { index ->
                            Item(
                                title = titles2[index],
                                isSelected = selectedTitles2[index],
                                onCheckedChange = { selectedTitles2[index] = it }
                            )
                        }
                    }
                }
            },
            confirmButton = {
                PrimaryButton(text = "Apply Now", onClick = {navController.navigate("productsFiltered")})
            }
    )
}

@Composable
fun Item(
    title: String,
    isSelected: Boolean,
    onCheckedChange: (Boolean) -> Unit
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize()
    ){
        Checkbox(checked = isSelected, onCheckedChange = onCheckedChange)
        Text(
            text = title,
            fontSize = 16.sp,
            modifier =  Modifier.padding(start = 2.dp)
        )
    }

}