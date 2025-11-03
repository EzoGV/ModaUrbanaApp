package com.example.modaurbanaapp.repository

import com.example.modaurbanaapp.data.remote.RetrofitClient
import com.example.modaurbanaapp.data.remote.dto.toDomain
import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product

class ProductRepositoryImpl : ProductRepository {
    override fun allCategories(): List<Category> = Category.entries

    override suspend fun featured(): List<Product> =
        RetrofitClient.api.products().map { it.toDomain() }.take(4)

    override suspend fun byCategory(category: Category): List<Product> =
        RetrofitClient.api.products().map { it.toDomain() }.filter { it.category == category }
}
