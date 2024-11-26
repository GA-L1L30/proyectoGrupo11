package com.example.appgrupo11.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo11.data.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {
    private val viewModelState = MutableStateFlow<HomeUiState>(HomeUiState.Loading())
    private val firestore = FirebaseFirestore.getInstance()
    private val productsCollection = "cart"


    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            HomeUiState.Loading()
        )

    init {
        refresh()
    }

    private fun refresh() {
        viewModelState.value = HomeUiState.Loading()

        viewModelScope.launch {
            try {
                val snapshot = firestore.collection(productsCollection).get().await()
                val products = snapshot.documents.mapNotNull { doc ->
                    doc.toObject<Product>()?.let { product ->
                        doc.id to product
                    }
                }

                val exclusiveOffers = products.filter {
                    it.second.title in listOf(
                        "Organic Bananas",
                        "Red Apple",
                        "Strawberry"
                    )
                }
                    .map { it.second }

                val bestSelling = products.filter {
                    it.second.title in listOf(
                        "Bell Pepper Red",
                        "Ginger",
                        "Strawberry"
                    )
                }
                    .map { it.second }

                viewModelState.value = HomeUiState.Success(
                    exclusiveOffers = exclusiveOffers,
                    bestSelling = bestSelling
                )
            } catch (e: Exception) {
                viewModelState.value =
                    HomeUiState.Error(message = "Error fetching data: ${e.message}")
            }

        }
    }
}