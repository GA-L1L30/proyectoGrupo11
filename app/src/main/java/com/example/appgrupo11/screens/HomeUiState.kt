package com.example.appgrupo11.screens

import com.example.appgrupo11.data.Product

interface HomeUiState {
    val isLoading: Boolean

    data class Success(
        override val isLoading: Boolean = false,
        val exclusiveOffers: List<Product> = emptyList(),
        val bestSelling: List<Product> = emptyList()
    ): HomeUiState

    data class Loading(
        override val isLoading: Boolean = true
    ) : HomeUiState
}

