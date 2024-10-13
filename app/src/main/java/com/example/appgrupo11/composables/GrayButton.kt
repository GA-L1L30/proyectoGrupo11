package com.example.appgrupo11.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.ui.theme.SecondaryColorButton
import com.example.appgrupo11.ui.theme.AppColors


@Composable
fun GrayButton(
    text : String,
    leftIcon: @Composable () -> Unit = {},
    onClick: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .height(70.dp)
        .clip(RoundedCornerShape(14.dp))
        .background(AppColors.Gray)
        .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ){
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)) {
            leftIcon()

            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight(weight = 600),
                color = Color(0xFF53B175),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}