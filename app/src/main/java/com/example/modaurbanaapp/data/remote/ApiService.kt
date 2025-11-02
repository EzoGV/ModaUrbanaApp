package com.example.modaurbanaapp.data.remote

import com.example.modaurbanaapp.data.remote.dto.*
import retrofit2.http.*

interface ApiService {

    @POST("login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    @GET("me")
    suspend fun me(): MeResponseDto
    // Nota: el token se inyecta por interceptor (no hace falta @Header aquí)

    @GET("products")
    suspend fun products(): List<ProductDto>
}
