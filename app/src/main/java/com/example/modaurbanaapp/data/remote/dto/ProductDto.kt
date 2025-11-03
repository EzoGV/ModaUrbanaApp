package com.example.modaurbanaapp.data.remote.dto

import com.squareup.moshi.Json
import com.example.modaurbanaapp.model.Product
import com.example.modaurbanaapp.model.Category

/**
 * Ajustado para mapear con tu modelo de dominio:
 * Product(id: Int, name: String, price: Int, oldPrice: Int?, imageUrl: String, category: Category)
 */
data class ProductDto(
    val id: Int,                                  // <- Int para calzar con Product.id
    val name: String,
    val price: Int,
    @Json(name = "old_price") val oldPrice: Int? = null,
    @Json(name = "image_url") val imageUrl: String,
    val category: String                          // viene como texto en la API
)

/** Mapeo DTO -> Dominio */
fun ProductDto.toDomain(): Product {
    // Intenta castear el texto a un enum Category; si no existe, usa un default
    val cat = try {
        Category.valueOf(category.uppercase())
    } catch (_: Exception) {
        Category.Polerones // ajusta tu default si usas otro
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
