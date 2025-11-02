package com.example.modaurbanaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.repository.ProductDataSource
import com.example.modaurbanaapp.ui.state.CatalogUiState
import com.example.modaurbanaapp.ui.state.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val repo: ProductDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(CatalogUiState(isLoading = true))
    val uiState = _uiState.asStateFlow()

    init { loadProducts() }

    private fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val category = _uiState.value.selectedCategory
                val base = repo.byCategory(category)
                val final = when (_uiState.value.order) {
                    Order.Featured -> base
                    Order.PriceAsc -> base.sortedBy { it.price }
                    Order.PriceDesc -> base.sortedByDescending { it.price }
                }
                _uiState.value = _uiState.value.copy(isLoading = false, products = final)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    fun changeCategory(category: Category) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
        loadProducts()
    }

    fun changeOrder(order: Order) {
        _uiState.value = _uiState.value.copy(order = order)
        loadProducts()
    }
}
