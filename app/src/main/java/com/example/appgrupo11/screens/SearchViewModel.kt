package com.example.appgrupo11.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SearchViewModel : ViewModel(){
    private val firestore = FirebaseFirestore.getInstance()
    private val productsCollection = "cart"

    private val _searchProducts = MutableStateFlow<List<Product>>(emptyList())
    val searchProducts: StateFlow<List<Product>> get() = _searchProducts


    init{
            fetchSearchProducts()
    }

    private fun fetchSearchProducts() {
        viewModelScope.launch {
            try {
                // Obtener productos desde Firestore
                val snapshot = firestore.collection(productsCollection).get().await()
                val products = snapshot.documents.mapNotNull { it.toObject<Product>() }

                // Actualizar el flujo de estado con los productos obtenidos
                _searchProducts.value = products
            } catch (e: Exception) {
                // Manejo de errores: en caso de fallo, dejar la lista vacía o mostrar un mensaje
                e.printStackTrace()
                _searchProducts.value = emptyList()
            }
        }
    }

   /*
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
    }*/
}