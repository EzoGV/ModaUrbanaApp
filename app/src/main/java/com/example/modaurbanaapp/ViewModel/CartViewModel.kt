package com.example.modaurbanaapp.ViewModel

import androidx.lifecycle.ViewModel
import com.example.modaurbanaapp.model.Product
import com.example.modaurbanaapp.repository.CartRepository

class CartViewModel : ViewModel() {
    val items = CartRepository.items
    fun add(p: Product) = CartRepository.add(p)
    fun removeOne(p: Product) = CartRepository.removeOne(p)
    fun clear() = CartRepository.clear()
    fun total(): Int = CartRepository.total()
}
