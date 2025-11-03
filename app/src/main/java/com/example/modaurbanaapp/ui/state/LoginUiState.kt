package com.example.modaurbanaapp.ui.state

/**
 * Estado de la pantalla de Login.
 * Contiene toda la información que se muestra o cambia en la UI.
 */
data class LoginUiState(
    val isLoading: Boolean = false,
    val isLogged: Boolean = false,
    val error: String? = null
)
