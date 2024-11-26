package com.example.appgrupo11.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class CategoryViewModel : ViewModel(){
    private val firestore = FirebaseFirestore.getInstance()
    private val productsCollection = "cart"

    private val _products = mutableListOf<Product>()
    val products: List<Product> get() = _products

    init{
        viewModelScope.launch {
            fetchProducts()
        }
    }

    private suspend fun fetchProducts(){
        try{
            val snapshot = firestore.collection(productsCollection)
                .whereEqualTo("category", "beverage")
                .get()
                .await()

            val items = snapshot.documents.mapNotNull { document ->
                val product = document.toObject(Product::class.java)
                product?.apply {
                    documentId = document.id
                }
            }

            _products.clear()
            _products.addAll(items)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    /*
    private fun fetchProducts() {
        _products.addAll(
            listOf(
                Product(id= 5, imageRes = R.drawable.coke,"Diet Coke",   "355ml, Price",  1.99,1),
                Product(id= 6, imageRes = R.drawable.sprite, "Sprite Can",  "325ml, Price", 1.50,1),
                Product(id= 7, imageRes = R.drawable.applejuice, "Apple Juice",  "325ml, Price", 1.50,1),
                Product(id= 8, imageRes = R.drawable.orangejuice,  "Orange Juice",  "2L, Price",  15.99,1),
                Product(id= 9, imageRes = R.drawable.redcoke,  "Coca Cola Can",  "325ml, Price",  4.99,1),
                Product(id= 10,imageRes = R.drawable.pepsi, "Pepsi Can",  "330ml, Price",  4.99,1),
            )
        )
    }*/
}

