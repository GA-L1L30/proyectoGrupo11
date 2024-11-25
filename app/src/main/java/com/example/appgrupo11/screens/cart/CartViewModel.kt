package com.example.appgrupo11.screens.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import com.example.appgrupo11.data.toMap
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class CartViewModel:ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val cartCollection = "cart"

    // Lista de productos en el carrito
    var cartItems = mutableStateListOf<Pair<String,Product>>()
        private set

    // Estado del total acumulado
    var totalAmount = mutableStateOf(0.0)
        private set

    // Inicialización del ViewModel
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

    /* Inicialización de los productos en el carrito
    init {
        // Aquí agregamos los productos iniciales al carrito
        cartItems.addAll(
            listOf(
                Product(1,R.drawable.pepper, "Bell Pepper Red", "1kg, Price", 4.99,1),
                Product(2,R.drawable.redegg, "Egg Chicken Red", "4pcs, Price", 1.99,1),
                Product(3,R.drawable.banana, "Organic Bananas", "12kg, Price", 3.00,1),
                Product(4,R.drawable.ginger, "Ginger", "250gm, Price", 2.99,1)
            )
        )
        calculateTotal() // Calcular el total inicial
    }
    */

    //Metodo para aumentar la cantidad de un producto
    fun onIncrease(item:Pair<String, Product>){
        val index = cartItems.indexOf(item)
        if(index != -1){
            val updatedProduct = cartItems[index].second.copy(quantity = cartItems[index].second.quantity +1)
            cartItems[index] = cartItems[index].first to updatedProduct
            calculateTotal()
            updateProductInFirestore(cartItems[index].first, updatedProduct)
        }
    }

    //Metodo para aumentar la cantidad de un producto
    fun onDecrease(item: Pair<String, Product>) {
        val index = cartItems.indexOf(item)
        if (index != -1 && cartItems[index].second.quantity > 0) {
            val updatedProduct = cartItems[index].second.copy(quantity = cartItems[index].second.quantity - 1)
            cartItems[index] = cartItems[index].first to updatedProduct
            calculateTotal()
            updateProductInFirestore(cartItems[index].first, updatedProduct)
        }
    }

    // Función para actualizar la cantidad de un producto
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

    // Función para recalcular el total del carrito
    private fun calculateTotal() {
        totalAmount.value = cartItems.sumOf { it.second.price * it.second.quantity }
    }

    // Función para actualizar un producto en Firestore
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

    // Función para eliminar un producto de Firestore
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





