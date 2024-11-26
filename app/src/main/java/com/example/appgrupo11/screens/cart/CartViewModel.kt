package com.example.appgrupo11.screens.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.appgrupo11.data.Product
import com.example.appgrupo11.data.toMap
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class CartViewModel:ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val cartCollection = "cart"

    var cartItems = mutableStateListOf<Pair<String,Product>>()
        private set

    var totalAmount = mutableStateOf(0.0)
        private set

    init{
        fetchCartItemsFromFirestore()
    }

    private fun fetchCartItemsFromFirestore(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val snapshot = firestore.collection(cartCollection).get().await()
                val items = snapshot.documents.mapNotNull { doc ->
                    val product = doc.toObject<Product>()
                    product?.let { doc.id to it }
                }

                cartItems.addAll(items)
                calculateTotal()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun onIncrease(item:Pair<String, Product>){
        val index = cartItems.indexOf(item)
        if(index != -1){
            val updatedProduct = cartItems[index].second.copy(quantity = cartItems[index].second.quantity +1)
            cartItems[index] = cartItems[index].first to updatedProduct
            calculateTotal()
            updateProductInFirestore(cartItems[index].first, updatedProduct)
        }
    }

    fun onDecrease(item: Pair<String, Product>) {
        val index = cartItems.indexOf(item)
        if (index != -1 && cartItems[index].second.quantity > 0) {
            val updatedProduct = cartItems[index].second.copy(quantity = cartItems[index].second.quantity - 1)
            cartItems[index] = cartItems[index].first to updatedProduct
            calculateTotal()
            updateProductInFirestore(cartItems[index].first, updatedProduct)
        }
    }

    fun updateQuantity(item: Pair<String, Product>, quantity: Int) {
        if (quantity >= 0) {
            val index = cartItems.indexOf(item)
            if (index != -1) {
                val updatedProduct = cartItems[index].second.copy(quantity = quantity)
                cartItems[index] = cartItems[index].first to updatedProduct
                calculateTotal()
                updateProductInFirestore(cartItems[index].first, updatedProduct)
            }
        }
    }

    private fun calculateTotal() {
        totalAmount.value = cartItems.sumOf { it.second.price * it.second.quantity }
    }

    private fun updateProductInFirestore(documentId: String, product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                firestore.collection(cartCollection).document(documentId)
                    .set(product)
                    .await()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun removeProductFromFirestore(documentId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                firestore.collection(cartCollection).document(documentId)
                    .delete()
                    .await()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun filterProductsWithQuantityGreaterThanOne() {
        cartItems = cartItems.filter { it.second.quantity >= 1 }.toMutableStateList()
    }

    fun saveOrderToCheckout() {
        filterProductsWithQuantityGreaterThanOne()
        val checkout = mapOf(
            "products" to cartItems.map { it.toMap() },
            "totalAmount" to totalAmount.value,
            "timestamp" to FieldValue.serverTimestamp()
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                firestore.collection("checkout").add(checkout).await()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

fun Pair<String, Product>.toMap(): Map<String, Any> {
    return mapOf(
        "productId" to this.first,
        "product" to this.second.toMap()
    )
}





