package com.example.appgrupo11.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appgrupo11.R

//Originalmente el Search estaba en FindProductsScreen, se copio y pego a composables
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Search(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholderText: String,
    onTrailingIconClick: () -> Unit,
){

    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {active = false},
        active = active,
        onActiveChange = {active = it},
        placeholder = { Text(text = placeholderText) },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        },
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.tune),
                contentDescription = "Tune Icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 2.dp)
                .clickable { onTrailingIconClick() }
            )
        }
    )
    {
    }
}
