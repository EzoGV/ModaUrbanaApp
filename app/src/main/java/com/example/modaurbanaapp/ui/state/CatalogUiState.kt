package com.example.modaurbanaapp.ui.state

import com.example.modaurbanaapp.model.Product
import com.example.modaurbanaapp.model.Category

data class CatalogUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null,
    val selectedCategory: Category = Category.Polerones,
    val order: Order = Order.Featured
)

enum class Order { Featured, PriceAsc, PriceDesc }
