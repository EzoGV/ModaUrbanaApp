package com.example.modaurbanaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.navigation.compose.rememberNavController
import com.example.modaurbanaapp.navigation.AppNavGraph
import com.example.modaurbanaapp.ui.components.BottomBar
import com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModaUrbanaAppTheme(darkTheme = false) {
                ModaUrbanaApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModaUrbanaApp() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "MODA URBANA") },
                actions = {
                    IconButton(onClick = { /* TODO search */ }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                    }
                    IconButton(onClick = { /* TODO account */ }) {
                        Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
                    }
                    IconButton(onClick = { /* TODO cart */ }) {
                        Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = { BottomBar(navController) },
        content = { inner ->
            androidx.compose.foundation.layout.Box(
                Modifier
                    .fillMaxSize()
                    .padding(inner)
            ) {
                AppNavGraph(navController)
            }
        }
    )
}
