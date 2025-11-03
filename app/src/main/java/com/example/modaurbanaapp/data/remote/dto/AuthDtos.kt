package com.example.modaurbanaapp.data.remote.dto

import com.squareup.moshi.Json

data class LoginRequestDto(val username: String, val password: String)

data class LoginResponseDto(
    @Json(name = "token") val accessToken: String
)

data class MeResponseDto(
    val id: Int,
    val username: String,
    val email: String
)
