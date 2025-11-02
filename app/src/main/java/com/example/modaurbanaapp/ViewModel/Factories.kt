package com.example.modaurbanaapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modaurbanaapp.repository.ProductDataSource
import com.example.modaurbanaapp.repository.ProductRepositoryImpl // remoto (material)

@Suppress("UNCHECKED_CAST")
class CatalogViewModelFactory(
    private val repo: ProductDataSource = ProductRepositoryImpl()
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CatalogViewModel(repo) as T
    }
}
