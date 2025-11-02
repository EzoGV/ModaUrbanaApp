package com.example.modaurbanaapp.data.remote

import com.example.modaurbanaapp.data.local.SessionManager
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.first
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val session: SessionManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // Bloqueamos solo para leer el token una vez (sencillo y suficiente)
        val token = runBlocking { session.tokenFlow.first() }

        val req = if (!token.isNullOrBlank()) {
            original.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            original
        }
        return chain.proceed(req)
    }
}
