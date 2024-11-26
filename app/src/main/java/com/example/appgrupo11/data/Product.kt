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

fun Product.toMap(): Map<String, Any> {
    return mapOf(
        "imageUrl" to this.imageUrl,
        "title" to this.title,
        "description" to this.description,
        "price" to this.price,
        "quantity" to this.quantity,
        "category" to this.category,
        "documentId" to this.documentId
    )
}

