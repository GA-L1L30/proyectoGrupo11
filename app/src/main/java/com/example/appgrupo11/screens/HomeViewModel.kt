package com.example.appgrupo11.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appgrupo11.R
import com.example.appgrupo11.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val viewModelState = MutableStateFlow<HomeUiState>(HomeUiState.Loading())


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
            try{
                val exclusiveOffers = listOf(
                    Product(R.drawable.banana, "Organic Bananas", "7pcs, Priceg", "$4.99",1),
                    Product(R.drawable.apple, "Red Apple", "1kg, Priceg", "$4.99",1),
                    Product(R.drawable.strawberry, "Strawberry", "7pcs, Priceg", "$6.99",1),
                )

                val bestSelling = listOf(
                    Product(R.drawable.pepper, "Bell Pepper Red", "7pcs, Priceg", "$4.99",1),
                    Product(R.drawable.ginger, "Ginger", "1kg, Priceg", "$4.99",1),
                    Product(R.drawable.strawberry, "Strawberry", "7pcs, Priceg", "$6.99",1),
                )

                viewModelState.value = HomeUiState.Success(
                    exclusiveOffers = exclusiveOffers,
                    bestSelling = bestSelling
                )
            }catch (e: Exception){
                viewModelState.value = HomeUiState.Error(message = "Error with data")
            }
        }
    }
}