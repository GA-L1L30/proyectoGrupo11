package com.example.appgrupo11.screens.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product

class CartViewModel:ViewModel() {
    // Lista de productos en el carrito
    var cartItems = mutableStateListOf<Product>()

    // Estado del total acumulado
    var totalAmount = mutableStateOf(0.0)

    // Inicialización de los productos en el carrito
    init {
        // Aquí agregamos los productos iniciales al carrito
        cartItems.addAll(
            listOf(
                Product(R.drawable.pepper, "Bell Pepper Red", "1kg, Price", 4.99,1),
                Product(R.drawable.redegg, "Egg Chicken Red", "4pcs, Price", 1.99,1),
                Product(R.drawable.banana, "Organic Bananas", "12kg, Price", 3.00,1),
                Product(R.drawable.ginger, "Ginger", "250gm, Price", 2.99,1)
            )
        )
        calculateTotal() // Calcular el total inicial
    }

    // Función para actualizar la cantidad de un producto

    fun updateQuantity(item: Product, quantity: Int) {
        if(quantity >= 0){
            var index = cartItems.indexOf(item)
            if(index != -1){
                cartItems[index].quantity = quantity
                calculateTotal() // Recalcular el total cuando cambie la cantidad
            }
        }
    }

    // Función para recalcular el total del carrito

    private fun calculateTotal() {
        totalAmount.value = cartItems.sumOf { it.price * it.quantity }
    }

}