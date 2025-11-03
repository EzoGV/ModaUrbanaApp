package com.example.modaurbanaapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("avatar_prefs")
class AvatarManager(private val context: Context) {
    companion object { private val KEY_AVATAR_URI = stringPreferencesKey("avatar_uri") }
    val avatarUri: Flow<String?> = context.dataStore.data.map { it[KEY_AVATAR_URI] }
    suspend fun saveAvatar(uri: String) { context.dataStore.edit { it[KEY_AVATAR_URI] = uri } }
}
