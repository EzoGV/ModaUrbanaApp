package com.example.modaurbanaapp.data.remote

import com.example.modaurbanaapp.data.local.SessionManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val session: SessionManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { session.tokenFlow.first() }
        val req = if (!token.isNullOrBlank())
            chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
        else chain.request()
        return chain.proceed(req)
    }
}
