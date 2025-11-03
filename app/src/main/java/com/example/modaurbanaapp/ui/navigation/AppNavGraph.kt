package com.example.modaurbanaapp.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.modaurbanaapp.ViewModel.*
import com.example.modaurbanaapp.ui.screens.*

/**
 * Navegador principal de la aplicación.
 */
@Composable
fun AppNavGraph(navController: NavHostController) {
    val context = LocalContext.current.applicationContext as Application

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route // Pantalla inicial
    ) {
        // --- LOGIN ---
        composable(Screen.Login.route) {
            val vm: LoginViewModel = viewModel(factory = LoginViewModelFactory(context))
            val uiState by vm.ui.collectAsState()

            LoginScreen(
                uiState = uiState,
                onLogin = { user, pass -> vm.login(user, pass) },
                onGoRegister = { navController.navigate(Screen.Register.route) }
            )

            if (uiState.isLogged) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        }

        // --- REGISTER ---
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegistered = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // --- HOME / CATÁLOGO ---
        composable(Screen.Home.route) {
            val catalogVm: CatalogViewModel = viewModel(factory = CatalogViewModelFactory())
            CatalogScreen()
        }

        // --- PROFILE ---
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}
