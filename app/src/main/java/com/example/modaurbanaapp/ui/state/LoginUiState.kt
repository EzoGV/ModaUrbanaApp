package com.example.modaurbanaapp.ui.state

data class LoginUiState(
    val isLoading: Boolean = false,
    val isLogged: Boolean = false,
    val error: String? = null
)
