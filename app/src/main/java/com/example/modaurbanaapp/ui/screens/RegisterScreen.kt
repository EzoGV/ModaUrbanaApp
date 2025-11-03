package com.example.modaurbanaapp.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RegisterScreen(onRegistered: () -> Unit) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Archivo temporal para la cámara
    val photoFile = remember {
        File(context.externalCacheDir, "profile_temp.jpg")
    }

    val photoUri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        photoFile
    )

    // --- Lanzadores ---
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) imageUri = photoUri
    }

    // Permiso de cámara
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) cameraLauncher.launch(photoUri)
    }

    // --- Diseño ---
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registro de Usuario", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        // Imagen elegida (si existe)
        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.height(16.dp))
        }

        // Botones para tomar o seleccionar foto
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = {
                val permissionCheck = ContextCompat.checkSelfPermission(
                    context, Manifest.permission.CAMERA
                )
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    cameraLauncher.launch(photoUri)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }) {
                Text("Cámara")
            }

            Button(onClick = { galleryLauncher.launch("image/*") }) {
                Text("Galería")
            }
        }

        Spacer(Modifier.height(24.dp))

        // Campos de usuario y contraseña
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
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        // Botón de registro
        Button(
            onClick = {
                if (username.isNotBlank() && password.isNotBlank()) {
                    onRegistered()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegisterScreen() {
    com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme {
        RegisterScreen(onRegistered = {})
    }
}
