package com.example.modaurbanaapp.repository

import com.example.modaurbanaapp.model.CartItem
import com.example.modaurbanaapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

object CartRepository {
    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items

    fun add(product: Product) {
        _items.update { current ->
            val idx = current.indexOfFirst { it.product.id == product.id }
            if (idx >= 0) {
                current.toMutableList().also { list ->
                    val old = list[idx]
                    list[idx] = old.copy(quantity = old.quantity + 1)
                }
            } else current + CartItem(product, 1)
        }
    }

    fun removeOne(product: Product) {
        _items.update { current ->
            val idx = current.indexOfFirst { it.product.id == product.id }
            if (idx < 0) current else {
                val list = current.toMutableList()
                val old = list[idx]
                val q = old.quantity - 1
                if (q <= 0) list.removeAt(idx) else list[idx] = old.copy(quantity = q)
                list
            }
        }
    }

    fun clear() { _items.value = emptyList() }

    fun total(): Int = _items.value.sumOf { it.product.price * it.quantity }
}
