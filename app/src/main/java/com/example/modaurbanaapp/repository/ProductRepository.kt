package com.example.modaurbanaapp.repository

import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product

interface ProductRepository {
    suspend fun featured(): List<Product>
    suspend fun byCategory(category: Category): List<Product>
    fun allCategories(): List<Category>
}
