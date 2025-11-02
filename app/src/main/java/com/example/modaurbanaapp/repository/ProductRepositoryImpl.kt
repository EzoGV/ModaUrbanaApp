package com.example.modaurbanaapp.repository

import com.example.modaurbanaapp.data.remote.RetrofitClient
import com.example.modaurbanaapp.data.remote.dto.toDomain
import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product

class ProductRepositoryImpl : ProductDataSource {

    override fun allCategories(): List<Category> = Category.entries

    override suspend fun byCategory(category: Category): List<Product> {
        val all = RetrofitClient.api.products().map { it.toDomain() }
        return all.filter { it.category == category }
    }

    override suspend fun featured(): List<Product> {
        val all = RetrofitClient.api.products().map { it.toDomain() }
        // La guía suele tomar "los primeros" como destacados
        return all.take(4)
    }
}
