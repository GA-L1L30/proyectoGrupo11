package com.example.appgrupo11.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appgrupo11.composables.GrayButton
import com.example.appgrupo11.composables.PrimaryButton

@Composable
fun OfferAcceptedScreen(){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        PrimaryButton(text = "Track Order"){}
        Spacer(modifier = Modifier.height(20.dp))
        GrayButton(text = "Back to home"){}
    }

}