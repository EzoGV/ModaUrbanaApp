package com.example.modaurbanaapp.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.modaurbanaapp.ViewModel.ProfileViewModel
import com.example.modaurbanaapp.ViewModel.ProfileViewModelFactory
import com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme

@Composable
fun ProfileScreen(
    profileVm: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(LocalContext.current.applicationContext as Application)
    )
) {
    val uri = profileVm.avatarUri.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Perfil de Usuario", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))

        if (uri != null) {
            AsyncImage(
                model = uri,
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(120.dp)
            )
        } else {
            Text("Sin foto de perfil", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.height(20.dp))
        Text("Bienvenido a Moda Urbana", style = MaterialTheme.typography.bodyLarge)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileScreen() {
    ModaUrbanaAppTheme {
        ProfileScreen()
    }
}
