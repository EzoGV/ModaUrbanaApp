package com.example.modaurbanaapp.data.remote.dto

import com.squareup.moshi.Json
import com.example.modaurbanaapp.model.Product
import com.example.modaurbanaapp.model.Category

data class ProductDto(
    val id: String,
    val name: String,
    val price: Int,
    @Json(name = "old_price") val oldPrice: Int?,
    @Json(name = "image_url") val imageUrl: String,
    val category: String
)

fun ProductDto.toDomain(): Product {
    val cat = runCatching { Category.valueOf(category) }.getOrDefault(Category.Polerones)
    return Product(id, name, price, oldPrice, imageUrl, cat)
}
