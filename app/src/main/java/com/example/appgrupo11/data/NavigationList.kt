package com.example.appgrupo11.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Icon

fun getNavigationList(): List<NavigationItem> {
    return listOf(
        NavigationItem(
            label = "Shop",
            title = "Shop",
            icon = { color ->
                Icon(imageVector = Icons.Outlined.Storefront, contentDescription = null, tint = color)
                   },
        ),
        NavigationItem(
            label = "Explore",
            title = "Find Products",
            icon = { color ->
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null, tint = color)
                   },
        ),
        NavigationItem(
            label = "Cart",
            title = "My Cart",
            icon = { color ->
                Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = null, tint = color)
                   },
        ),
        NavigationItem(
            label = "Favourite",
            title = "Favourites",
            icon = {color ->
                Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null, tint = color) },
        ),
        NavigationItem(
            label = "Account",
            title = "Account",
            icon = { color ->
                Icon(imageVector = Icons.Outlined.Person, contentDescription = null, tint = color) },
        )

    )
}