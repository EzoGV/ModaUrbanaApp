package com.example.modaurbanaapp.data.local

import android.content.Context
import android.net.Uri

class AvatarManager(context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    private val KEY_AVATAR_URI = "avatar_uri"

    fun get(): Uri? {
        val s = prefs.getString(KEY_AVATAR_URI, null)
        return s?.let { Uri.parse(it) }
    }

    fun set(uri: Uri) {
        prefs.edit().putString(KEY_AVATAR_URI, uri.toString()).apply()
    }

    fun clear() {
        prefs.edit().remove(KEY_AVATAR_URI).apply()
    }
}
