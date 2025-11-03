package com.example.modaurbanaapp.data.local

import android.content.Context

class SessionManager(context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    private val KEY_TOKEN = "auth_token"

    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)

    fun setToken(value: String?) {
        prefs.edit().apply {
            if (value == null) remove(KEY_TOKEN) else putString(KEY_TOKEN, value)
        }.apply()
    }
}
