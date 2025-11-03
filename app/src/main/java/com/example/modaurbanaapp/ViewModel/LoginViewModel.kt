package com.example.modaurbanaapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modaurbanaapp.data.local.SessionManager
import com.example.modaurbanaapp.data.remote.RetrofitClient
import com.example.modaurbanaapp.data.remote.dto.LoginRequestDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLogged: Boolean = false
)

class LoginViewModel(private val session: SessionManager) : ViewModel() {
    val _ui = MutableStateFlow(LoginUiState())
    val ui = _ui.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _ui.value = LoginUiState(isLoading = true)
            try {
                val resp = RetrofitClient.api.login(LoginRequestDto(username, password))
                session.saveToken(resp.accessToken)
                _ui.value = LoginUiState(isLogged = true)
            } catch (e: Exception) {
                _ui.value = LoginUiState(error = e.message ?: "Error de login")
            }
        }
    }
}
