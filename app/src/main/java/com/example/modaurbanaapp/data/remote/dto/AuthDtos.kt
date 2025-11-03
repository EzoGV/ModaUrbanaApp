package com.example.modaurbanaapp.data.remote.dto

// DTOs de autenticación y perfil
data class LoginRequestDto(
    val email: String,
    val password: String
)

data class LoginResponseDto(
    val authToken: String,
    val user: MeResponseDto
)

data class MeResponseDto(
    val id: Int,
    val email: String,
    val name: String?,
    val avatarUrl: String?
)
