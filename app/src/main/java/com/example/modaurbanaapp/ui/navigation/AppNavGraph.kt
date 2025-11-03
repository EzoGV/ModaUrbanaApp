package com.example.modaurbanaapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.modaurbanaapp.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route)   { LoginScreen(navController) }
        composable(Screen.Register.route){ RegisterScreen(navController) }
        composable(Screen.Home.route)    { HomeScreen(navController) }
        composable(Screen.Catalog.route) { CatalogScreen() }
        composable(Screen.Cart.route)    { CartScreen() }      // << NUEVO
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}
