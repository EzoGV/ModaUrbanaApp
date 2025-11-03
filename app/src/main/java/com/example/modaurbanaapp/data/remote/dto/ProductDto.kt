package com.example.modaurbanaapp.data.remote.dto

import com.squareup.moshi.Json
import com.example.modaurbanaapp.model.Product
import com.example.modaurbanaapp.model.Category


data class ProductDto(
    val id: Int,
    val name: String,
    val price: Int,
    @Json(name = "old_price") val oldPrice: Int? = null,
    @Json(name = "image_url") val imageUrl: String,
    val category: String
)

fun ProductDto.toDomain(): Product {
    val cat = try {
        Category.valueOf(category.uppercase())
    } catch (_: Exception) {
        Category.Polerones
    }
    return Product(
        id = id,
        name = name,
        price = price,
        oldPrice = oldPrice,
        imageUrl = imageUrl,
        category = cat
    )
}
