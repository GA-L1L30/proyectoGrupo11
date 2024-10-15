package com.example.appgrupo11.screens.favorites
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product

class FavoritesViewModel: ViewModel() {

    val favoritesItems = mutableStateListOf<Product>()


    // Inicialización de los productos
    init {
        // Aquí agregamos los productos a la list de favoritos
        favoritesItems.addAll(
            listOf(
                Product(R.drawable.sprite, "Sprite Can", "325ml, Price", 1.50,1),
                Product(R.drawable.coke, "Diet Coke", "355ml, Price", 1.99,1),
                Product(R.drawable.applejuice, "Apple & Grape Juice", "2L, Price", 15.50,1),
                Product(R.drawable.redcoke, "Coca cola Can", "325ml, Price", 4.99,1),
                Product(R.drawable.pepsi, "Pepsi Can", "330ml, Price", 4.99,1)
            )
        )

    }
}










