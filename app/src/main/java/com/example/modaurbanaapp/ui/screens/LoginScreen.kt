package com.example.modaurbanaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.modaurbanaapp.ViewModel.LoginUiState
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onLogin: (String, String) -> Unit,
    onGoRegister: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(20.dp))

        Button(
            onClick = { onLogin(username, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(18.dp)
                )
            } else {
                Text("Ingresar")
            }
        }

        if (uiState.error != null) {
            Text(uiState.error, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(Modifier.height(20.dp))

        TextButton(onClick = onGoRegister) {
            Text("¿No tienes cuenta? Regístrate aquí")
        }
    }

}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme {
        LoginScreen(
            uiState = com.example.modaurbanaapp.ui.state.LoginUiState(),
            onLogin = { _, _ -> },
            onGoRegister = {}
        )
    }
}
