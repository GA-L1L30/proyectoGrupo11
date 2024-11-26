package com.example.appgrupo11.screens.favorites
import androidx.lifecycle.ViewModel
import com.example.appgrupo11.data.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.lifecycle.viewModelScope


class FavoritesViewModel: ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val productsCollection = "cart"

    private val _favoritesItems = MutableStateFlow<List<Product>>(emptyList())
    val favoritesItems: StateFlow<List<Product>> get() = _favoritesItems

    init {
        fetchFavoriteProducts()
    }

    private fun fetchFavoriteProducts() {
        viewModelScope.launch {
            try {
                val snapshot = firestore.collection(productsCollection).get().await()
                val allProducts = snapshot.documents.mapNotNull { it.toObject<Product>() }

                val favoriteProducts = allProducts.filter { product ->
                    product.title in listOf(
                        "Sprite Can",
                        "Diet Coke",
                        "Apple & Grape Juice",
                        "Coca Cola Can",
                        "Pepsi Can"
                    )
                }

                _favoritesItems.value = favoriteProducts
            } catch (e: Exception) {
                e.printStackTrace()
                _favoritesItems.value = emptyList()
            }
        }
    }
}










