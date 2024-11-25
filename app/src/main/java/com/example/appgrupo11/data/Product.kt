package com.example.appgrupo11.data

data class Product(
    val imageUrl: String = "",
    val title: String = "",
    val description: String = "",
    val price: Double = 0.0,
    var quantity: Int = 0,
    var category: String = ""
) {
    var documentId: String = ""
}

