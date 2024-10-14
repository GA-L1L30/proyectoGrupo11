package com.example.appgrupo11.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel(){
    private val _products = mutableListOf<Product>()
    val products: List<Product> get() = _products

    init{
        viewModelScope.launch {
            fetchProducts()
        }
    }

    private fun fetchProducts() {
        _products.addAll(
            listOf(
                Product(imageRes = R.drawable.coke, title=  "Diet Coke",  description = "355ml, Price", price = "$1.99",1),
                Product(imageRes = R.drawable.sprite, title=  "Sprite Can", description = "325ml, Price", price = "$1.50",1),
                Product(imageRes = R.drawable.applejuice, title=  "Apple Juice", description = "325ml, Price", price = "$1.50",1),
                Product(imageRes = R.drawable.orangejuice, title=  "Orange Juice", description = "2L, Price", price = "$15.99",1),
                Product(imageRes = R.drawable.redcoke, title=  "Coca Cola Can", description = "325ml, Price", price = "$4.99",1),
                Product(imageRes = R.drawable.pepsi, title=  "Pepsi Can", description = "330ml, Price", price = "$4.99",1),
            )
        )
    }
}