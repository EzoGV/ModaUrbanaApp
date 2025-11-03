package com.example.modaurbanaapp.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Catalog : Screen("catalog")
    object Profile : Screen("profile")
}
