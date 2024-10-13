package com.example.appgrupo11.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appgrupo11.ui.theme.SecondaryColorButton
import com.example.appgrupo11.ui.theme.StarsColor

fun getProductDescriptionList() : List<ExpandableListItem> {
    return listOf(
        ExpandableListItem({ isExpanded ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Product Detail", fontSize = 16.sp, fontWeight = FontWeight(500))
                Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = null, modifier = Modifier
                    .size(18.dp)
                    .graphicsLayer { rotationZ = if (isExpanded) 270f else 180f })
            }
        }, {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Apples are nutritious. Apples may be good for weight loss. apples may be good for your heart. As part of a healtful and varied diet.", fontSize = 13.sp, color = Color.Gray)
            }
        }),
        ExpandableListItem({ isExpanded ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Nutritions", fontSize = 16.sp, fontWeight = FontWeight(500))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "100gr", fontSize = 9.sp, color = Color.DarkGray, modifier = Modifier.background(color = SecondaryColorButton, shape = RoundedCornerShape(7.dp)).padding(horizontal = 5.dp), fontWeight = FontWeight(500))
                    Spacer(modifier = Modifier.size(10.dp))
                    Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = null, modifier = Modifier
                        .size(18.dp)
                        .graphicsLayer { rotationZ = if (isExpanded) 270f else 180f })
                }

            }
        }, {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Nutritions", fontSize = 13.sp, color = Color.Gray)
            }
        }),
        ExpandableListItem({ isExpanded ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Reviews", fontSize = 16.sp, fontWeight = FontWeight(500))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    for(i in 1..5) {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = null, modifier = Modifier.size(18.dp), tint = StarsColor)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = null, modifier = Modifier
                        .size(18.dp)
                        .graphicsLayer { rotationZ = if (isExpanded) 270f else 180f })
                }
            }
        }, {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Reviews", fontSize = 13.sp, color = Color.Gray)
            }
        }),

    )
}