package com.example.appgrupo11.screens.favorites
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.lifecycle.viewModelScope


class FavoritesViewModel: ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val productsCollection = "cart" // Cambiar si la colección tiene otro nombre

    private val _favoritesItems = MutableStateFlow<List<Product>>(emptyList())
    val favoritesItems: StateFlow<List<Product>> get() = _favoritesItems

    init {
        fetchFavoriteProducts()
    }

    private fun fetchFavoriteProducts() {
        viewModelScope.launch {
            try {
                // Obtener productos desde Firestore
                val snapshot = firestore.collection(productsCollection).get().await()
                val allProducts = snapshot.documents.mapNotNull { it.toObject<Product>() }

                // Filtrar los productos favoritos según los criterios indicados
                val favoriteProducts = allProducts.filter { product ->
                    product.title in listOf(
                        "Sprite Can",
                        "Diet Coke",
                        "Apple & Grape Juice",
                        "Coca cola Can",
                        "Pepsi Can"
                    )
                }

                // Actualizar el flujo con los productos favoritos filtrados
                _favoritesItems.value = favoriteProducts
            } catch (e: Exception) {
                // Manejo de errores: en caso de fallo, dejar la lista vacía o mostrar un mensaje
                e.printStackTrace()
                _favoritesItems.value = emptyList()
            }
        }
    }


    // Inicialización de los productos

    /*
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

    }*/
}










