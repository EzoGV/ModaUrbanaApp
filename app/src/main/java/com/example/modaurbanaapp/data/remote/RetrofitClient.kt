package com.example.modaurbanaapp.data.remote

import android.content.Context
import com.example.modaurbanaapp.data.local.SessionManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dummyjson.com/"

    private lateinit var _api: ApiService
    val api: ApiService get() = _api

    fun init(context: Context) {
        val session = SessionManager(context)
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(AuthInterceptor(session))
            .build()

        _api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(ApiService::class.java)
    }
}
