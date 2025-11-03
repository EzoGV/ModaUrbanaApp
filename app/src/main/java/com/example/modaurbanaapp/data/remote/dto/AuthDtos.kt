package com.example.modaurbanaapp.data.remote.dto

import com.squareup.moshi.Json

data class LoginRequestDto(val username: String, val password: String)
data class LoginResponseDto(val token: String)
data class MeResponseDto(val id: Int, val name: String, val email: String)
data class ProductDto(val id: Int, val name: String, val price: Int)