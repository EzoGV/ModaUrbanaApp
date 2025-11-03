package com.example.modaurbanaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import com.example.modaurbanaapp.ui.navigation.AppNavGraph
import com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModaUrbanaAppTheme(darkTheme = false) {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}
