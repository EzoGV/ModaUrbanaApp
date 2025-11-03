package com.example.modaurbanaapp.ViewModel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modaurbanaapp.data.local.AvatarManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel(app: Application) : AndroidViewModel(app) {

    private val avatarManager = AvatarManager(getApplication())

    private val _avatarUri: MutableStateFlow<Uri?> = MutableStateFlow(null)
    val avatarUri: StateFlow<Uri?> = _avatarUri

    init {
        // Carga inicial del avatar persistido (sin corrutinas, es lectura rápida)
        _avatarUri.value = avatarManager.get()
    }

    /** Actualiza el avatar y lo persiste */
    fun updateAvatar(uri: Uri) {
        _avatarUri.value = uri
        avatarManager.set(uri)
    }

    /** (Opcional) Limpia el avatar guardado */
    fun clearAvatar() {
        avatarManager.clear()
        _avatarUri.value = null
    }
}


