package com.example.modaurbanaapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modaurbanaapp.data.local.AvatarManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(private val avatar: AvatarManager) : ViewModel() {
    val avatarUri = avatar.avatarUri.stateIn(viewModelScope, SharingStarted.Eagerly, null)

    fun saveAvatar(uri: String) {
        viewModelScope.launch { avatar.saveAvatar(uri) }
    }
}
