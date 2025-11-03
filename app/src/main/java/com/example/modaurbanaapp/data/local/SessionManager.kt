package com.example.modaurbanaapp.data.local

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "auth_token"
    }

    // ✅ Guarda el token JWT o similar
    fun saveAuthToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    // ✅ Obtiene el token guardado
    fun getAuthToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    // ✅ Limpia sesión (por ejemplo, al cerrar sesión)
    fun clearSession() {
        prefs.edit().clear().apply()
    }
}
