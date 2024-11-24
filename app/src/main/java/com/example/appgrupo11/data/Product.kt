package com.example.appgrupo11.data

data class Product(
    val imageRes: Int,
    val title: String,
    val description: String,
    val price: Double,
    var quantity: Int,
){
    constructor(): this(0,"","",0.0,0)
}
