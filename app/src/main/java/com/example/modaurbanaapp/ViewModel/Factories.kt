package com.example.modaurbanaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modaurbanaapp.repository.LocalProductRepository
import com.example.modaurbanaapp.repository.ProductRepository

@Suppress("UNCHECKED_CAST")
class CatalogViewModelFactory(
    private val repo: ProductRepository = LocalProductRepository()
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatalogViewModel(repo) as T
    }
}
