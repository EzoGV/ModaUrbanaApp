package com.example.modaurbanaapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Storefront
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PersonAdd

import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.PersonAdd

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(Screen.Home, Screen.Catalog)

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            val (iconSelected, iconUnselected, label) = when (screen) {
                Screen.Home -> Triple(Icons.Filled.Home, Icons.Outlined.Home, "Inicio")
                Screen.Catalog -> Triple(Icons.Filled.Storefront, Icons.Outlined.Storefront, "Catálogo")
                Screen.Profile -> Triple(Icons.Filled.Person, Icons.Outlined.Person, "Perfil")
                Screen.Login -> Triple(Icons.Filled.Lock, Icons.Outlined.Lock, "Login")
                Screen.Register -> Triple(Icons.Filled.PersonAdd, Icons.Outlined.PersonAdd, "Registro")
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
                icon = {
                    Icon(
                        imageVector = if (selected) iconSelected else iconUnselected,
                        contentDescription = label
                    )
                },
                label = { Text(label) }
            )
        }
    }
}
