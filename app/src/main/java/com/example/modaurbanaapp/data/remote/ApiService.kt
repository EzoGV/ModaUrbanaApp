package com.example.modaurbanaapp.data.remote

import com.example.modaurbanaapp.data.remote.dto.*
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")            // ejemplo dummyjson: /auth/login
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    @GET("auth/me")                // si tu API no tiene /me, puedes omitir
    suspend fun me(): MeResponseDto

    @GET("products")               // o la ruta que defina tu API
    suspend fun products(): List<ProductDto>
}
