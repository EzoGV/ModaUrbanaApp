package com.example.modaurbanaapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.modaurbanaapp.ui.navigation.Screen

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        Screen.Home,
        Screen.Catalog,
        Screen.Cart,
        Screen.Profile
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            val (iconSel, iconUnsel, label) = when (screen) {
                Screen.Home    -> Triple(Icons.Filled.Home, Icons.Outlined.Home, "Inicio")
                Screen.Catalog -> Triple(Icons.Filled.Store, Icons.Outlined.Store, "Catálogo")
                Screen.Cart    -> Triple(Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart, "Carrito")
                Screen.Profile -> Triple(Icons.Filled.Person, Icons.Outlined.Person, "Perfil")
                else -> Triple(Icons.Filled.Home, Icons.Outlined.Home, "")
            }

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(screen.route) {
                            popUpTo(Screen.Home.route) { inclusive = false }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(if (selected) iconSel else iconUnsel, contentDescription = label) },
                label = { Text(label) }
            )
        }
    }
}
