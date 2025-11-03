package com.example.modaurbanaapp
// a
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.modaurbanaapp.ui.components.BottomBar
import com.example.modaurbanaapp.ui.navigation.AppNavGraph
import com.example.modaurbanaapp.ui.navigation.Screen
import com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ModaUrbanaAppRoot()
        }
    }
}

@Composable
fun ModaUrbanaAppRoot() {
    ModaUrbanaAppTheme {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        // Rutas que SÍ muestran la BottomBar
        val routesWithBottomBar = remember {
            setOf(
                Screen.Home.route,
                Screen.Catalog.route,
                Screen.Profile.route
            )
        }

        Scaffold(
            bottomBar = {
                if (currentRoute in routesWithBottomBar) {
                    BottomBar(navController = navController)
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavGraph(navController = navController)
            }
        }
    }
}
