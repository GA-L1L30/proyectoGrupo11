package com.example.appgrupo11.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.filled.MapsUgc
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.BusinessCenter
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.LocalActivity
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications


fun getAccountOptions(): List<AccountOption> {
    return listOf(
        AccountOption(
            leftIcon = Icons.Outlined.BusinessCenter,
            title = "Orders",
        ),
        AccountOption(
            leftIcon = Icons.Outlined.Badge,
            title = "My Details",
        ),
        AccountOption(
            leftIcon = Icons.Outlined.LocationOn,
            title = "Delivery Address",
        ),
        AccountOption(
            leftIcon = Icons.Default.Payment,
            title = "Payment Methods",
        ),
        AccountOption(
            leftIcon = Icons.Outlined.LocalActivity,
            title = "Promo Cards",
        ),
        AccountOption(
            leftIcon = Icons.Outlined.Notifications,
            title = "Notifications",
        ),
        AccountOption(
            leftIcon = Icons.AutoMirrored.Outlined.HelpOutline,
            title = "Help",
        ),
    )
}