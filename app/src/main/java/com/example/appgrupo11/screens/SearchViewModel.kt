package com.example.appgrupo11.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel(){
    private val _searchProducts = MutableStateFlow<List<Product>>(emptyList())
    val searchProducts: StateFlow<List<Product>> get() =_searchProducts

    init{
        viewModelScope.launch{
            fetchSearchProducts()
        }
    }

    private fun fetchSearchProducts() {
        val products = listOf(
            Product(imageRes = R.drawable.redegg, title = "Egg Red", description = "4pcs, Price", price = 1.99,1),
            Product(imageRes = R.drawable.whiteegg, title = "Egg White", description = "180g, Price", price = 1.50,1),
            Product(imageRes = R.drawable.eggpasta, title = "Egg Pasta", description = "30gm, Price", price = 15.99,1),
            Product(imageRes = R.drawable.eggnoddle, title = "Egg Noddle", description = "40gm", price = 15.99,1 ),
            Product(imageRes = R.drawable.mayo, title = "Egg Mayo", description = "250ml", price = 7.99,1),
            Product(imageRes = R.drawable.spaghetti, title = "Egg Spaghetti", description = "350g", price = 4.99,1),
        )
        _searchProducts.value = products
    }
}