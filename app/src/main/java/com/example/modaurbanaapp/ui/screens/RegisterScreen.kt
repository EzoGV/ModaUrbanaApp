package com.example.modaurbanaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.modaurbanaapp.ViewModel.RegisterViewModel
import com.example.modaurbanaapp.ViewModel.RegisterViewModelFactory
import com.example.modaurbanaapp.ui.navigation.Screen

@Composable
fun RegisterScreen(navController: NavHostController) {
    val vm: RegisterViewModel = viewModel(factory = RegisterViewModelFactory())
    val ui by vm.ui.collectAsStateWithLifecycle()

    if (ui.isSuccess) {
        // Paso de información mínimo: navegamos a Profile y/o Login
        // (Puedes elegir: a Login o directo a Profile. Aquí vamos a Profile)
        navController.navigate(Screen.Profile.route) {
            popUpTo(Screen.Register.route) { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Crear cuenta", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = ui.name,
            onValueChange = vm::onNameChange,
            label = { Text("Nombre") },
            isError = ui.nameError != null
        )
        if (ui.nameError != null) Text(ui.nameError!!, color = MaterialTheme.colorScheme.error)

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = ui.email,
            onValueChange = vm::onEmailChange,
            label = { Text("Email") },
            isError = ui.emailError != null
        )
        if (ui.emailError != null) Text(ui.emailError!!, color = MaterialTheme.colorScheme.error)

        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = ui.password,
            onValueChange = vm::onPasswordChange,
            label = { Text("Contraseña") },
            isError = ui.passwordError != null
        )
        if (ui.passwordError != null) Text(ui.passwordError!!, color = MaterialTheme.colorScheme.error)

        Spacer(Modifier.height(16.dp))
        Button(onClick = vm::submit) { Text("Registrarme") }

        Spacer(Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate(Screen.Login.route) }) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }
    }
}
