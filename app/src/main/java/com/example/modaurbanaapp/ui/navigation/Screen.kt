package com.example.modaurbanaapp.ui.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Home : Screen("home")
    data object Catalog : Screen("catalog")
    data object Cart : Screen("cart")       // << NUEVO
    data object Profile : Screen("profile")
}
