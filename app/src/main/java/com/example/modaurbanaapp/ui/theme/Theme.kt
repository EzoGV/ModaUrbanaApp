package com.example.modaurbanaapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = DarkGreen,
    onPrimary = TextWhite,
    background = CreamBackground, // Fondo crema
    onBackground = TextWhite,      // Texto blanco sobre fondo crema
    surface = White,               // Tarjetas blancas
    onSurface = DarkGreen          // Texto verde oscuro dentro de tarjetas
)

@Composable
fun ModaUrbanaAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
