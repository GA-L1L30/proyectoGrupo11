package com.example.appgrupo11.data

import androidx.compose.runtime.Composable

data class ExpandableListItem(
    val header: @Composable (isExpanded: Boolean ) -> Unit,
    val content: @Composable () -> Unit,
)
