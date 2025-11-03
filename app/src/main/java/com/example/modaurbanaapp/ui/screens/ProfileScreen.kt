package com.example.modaurbanaapp.ui.screens

import android.app.Application
import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.modaurbanaapp.ViewModel.ProfileViewModel
import com.example.modaurbanaapp.ViewModel.ProfileViewModelFactory
import com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme
import java.io.File

@Composable
fun ProfileScreen(
    profileVm: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(LocalContext.current.applicationContext as Application)
    )
) {
    val context = LocalContext.current
    val avatarUri = profileVm.avatarUri.collectAsState().value

    // ----- Galería -----
    val pickImage = rememberLauncherForActivityResult(GetContent()) { uri ->
        uri?.let { profileVm.updateAvatar(it) }
    }

    // ----- Cámara -----
    var cameraOutputUri by remember { mutableStateOf<Uri?>(null) }
    val takePicture = rememberLauncherForActivityResult(TakePicture()) { ok ->
        if (ok) {
            cameraOutputUri?.let { profileVm.updateAvatar(it) }  // evita smart cast
        }
    }

    // Permiso de cámara
    val requestCameraPermission = rememberLauncherForActivityResult(RequestPermission()) { granted ->
        if (granted) {
            cameraOutputUri = createTempImageUri(context)
            cameraOutputUri?.let { takePicture.launch(it) }
        }
    }

    fun openCamera() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestCameraPermission.launch(android.Manifest.permission.CAMERA)
        } else {
            cameraOutputUri = createTempImageUri(context)
            cameraOutputUri?.let { takePicture.launch(it) }
        }
    }

    // ----- UI -----
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Perfil de Usuario", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))

        if (avatarUri != null) {
            AsyncImage(
                model = avatarUri,
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(120.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "Simulación: mostrando productos similares a tu estilo.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            Text("Sin foto de perfil", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { pickImage.launch("image/*") }) { Text("Elegir de galería") }
            Button(onClick = { openCamera() }) { Text("Tomar foto") }
        }
    }
}

/** Función normal (no composable) para crear un URI temporal con FileProvider */
private fun createTempImageUri(context: Context): Uri {
    val file = File.createTempFile("avatar_", ".jpg", context.cacheDir).apply { deleteOnExit() }
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileScreen() {
    ModaUrbanaAppTheme { ProfileScreen() }
}
