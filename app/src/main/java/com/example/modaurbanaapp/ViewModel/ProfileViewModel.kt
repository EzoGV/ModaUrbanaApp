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

    private val avatarManager = AvatarManager(app) // ✅ aquí se usa el Application
    private val _avatarUri = MutableStateFlow<Uri?>(null)
    val avatarUri: StateFlow<Uri?> = _avatarUri

    init {
        // Si tienes persistencia:
        // viewModelScope.launch { _avatarUri.value = avatarManager.load() }
    }

    fun updateAvatar(uri: Uri) {
        viewModelScope.launch {
            _avatarUri.value = uri
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
