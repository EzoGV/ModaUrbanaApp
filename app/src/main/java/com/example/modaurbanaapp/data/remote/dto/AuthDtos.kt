package com.example.modaurbanaapp.data.remote.dto

import com.squareup.moshi.Json

data class LoginRequestDto(
    val email: String,
    val password: String
)

data class LoginResponseDto(
    @Json(name = "access_token") val accessToken: String
)

data class MeResponseDto(
    val id: String,
    val name: String,
    val email: String
)
