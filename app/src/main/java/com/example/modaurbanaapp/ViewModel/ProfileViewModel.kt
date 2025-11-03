package com.example.modaurbanaapp.ViewModel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(app: Application) : AndroidViewModel(app) {

    private val _avatarUri = MutableStateFlow<Uri?>(null)
    val avatarUri: StateFlow<Uri?> = _avatarUri

    init {
        // TODO: si tienes AvatarManager/DataStore, carga aquí el valor persistido
        // viewModelScope.launch { _avatarUri.value = avatarManager.load() }
    }

    /** Actualiza el avatar y (opcional) persiste */
    fun updateAvatar(uri: Uri) {
        viewModelScope.launch {
            _avatarUri.value = uri
            // TODO: si tienes AvatarManager/DataStore, persiste aquí:
            // avatarManager.save(uri)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(app) as T
    }
}
