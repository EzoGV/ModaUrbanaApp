package com.example.modaurbanaapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.modaurbanaapp.ui.state.RegisterUiState

class RegisterViewModel : ViewModel() {

    private val _ui = MutableStateFlow(RegisterUiState())
    val ui: StateFlow<RegisterUiState> = _ui

    fun onNameChange(value: String) {
        _ui.update { it.copy(name = value, nameError = null) }
    }

    fun onEmailChange(value: String) {
        _ui.update { it.copy(email = value, emailError = null) }
    }

    fun onPasswordChange(value: String) {
        _ui.update { it.copy(password = value, passwordError = null) }
    }

    fun submit() {
        val state = _ui.value
        var nameErr: String? = null
        var emailErr: String? = null
        var passErr: String? = null

        if (state.name.isBlank()) nameErr = "Ingresa tu nombre"
        if (!state.email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) emailErr = "Email no válido"
        if (state.password.length < 6) passErr = "Mínimo 6 caracteres"

        if (nameErr != null || emailErr != null || passErr != null) {
            _ui.update { it.copy(nameError = nameErr, emailError = emailErr, passwordError = passErr) }
            return
        }

        _ui.update { it.copy(isSuccess = true) }
    }
}

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel() as T
    }
}