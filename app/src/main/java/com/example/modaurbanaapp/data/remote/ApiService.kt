package com.example.modaurbanaapp.data.remote

import com.example.modaurbanaapp.data.remote.dto.*
import retrofit2.http.*

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDto): LoginResponseDto

    @GET("auth/me")
    suspend fun me(): MeResponseDto

    @GET("products")
    suspend fun products(): List<ProductDto>
}
