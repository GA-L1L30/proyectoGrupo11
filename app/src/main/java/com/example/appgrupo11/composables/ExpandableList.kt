package com.example.appgrupo11.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appgrupo11.data.ExpandableListItem

@Composable
fun ExpandableList(modifier: Modifier = Modifier, items: List<ExpandableListItem>) {
    var expandedItemIndex by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            HorizontalDivider()
            ListItem(item, index == expandedItemIndex) {
                expandedItemIndex = if (expandedItemIndex == index) -1 else index
            }
        }
    }
}

@Composable
fun ListItem(item: ExpandableListItem, isExpanded: Boolean, onClick: () -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()

                .clickable(onClick = onClick)
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            item.header(isExpanded)
        }
        if (isExpanded) {
            Box(modifier = Modifier.padding(bottom = 12.dp)) {
                item.content()
            }
        }
    }
}