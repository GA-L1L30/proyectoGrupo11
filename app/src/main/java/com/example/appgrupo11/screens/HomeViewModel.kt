package com.example.appgrupo11.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.toObject
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
                        doc.id to product // Mapear a un par de (DocumentID, Product)
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


            /*
        viewModelScope.launch {
            try{
                val exclusiveOffers = listOf(
                    Product(R.drawable.banana, "Organic Bananas", "7pcs, Priceg", 4.99,1),
                    Product(R.drawable.apple, "Red Apple", "1kg, Priceg", 4.99,1),
                    Product(R.drawable.strawberry, "Strawberry", "7pcs, Priceg", 6.99,1),
                )

                val bestSelling = listOf(
                    Product(R.drawable.pepper, "Bell Pepper Red", "7pcs, Priceg", 4.99,1),
                    Product(R.drawable.ginger, "Ginger", "1kg, Priceg", 4.99,1),
                    Product(R.drawable.strawberry, "Strawberry", "7pcs, Priceg", 6.99,1),
                )

                viewModelState.value = HomeUiState.Success(
                    exclusiveOffers = exclusiveOffers,
                    bestSelling = bestSelling
                )
            }catch (e: Exception){
                viewModelState.value = HomeUiState.Error(message = "Error with data")
            }
        }*/
        }
    }
}