package com.example.modaurbanaapp.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.modaurbanaapp.data.local.AvatarManager
import com.example.modaurbanaapp.data.local.SessionManager
import com.example.modaurbanaapp.repository.LocalProductRepository
import com.example.modaurbanaapp.repository.ProductRepository
import com.example.modaurbanaapp.repository.ProductRepositoryImpl
import com.example.modaurbanaapp.ViewModel.CatalogViewModel


@Suppress("UNCHECKED_CAST")
class CatalogViewModelFactory(
    private val repo: ProductRepository = LocalProductRepository()
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        CatalogViewModel(repo) as T
}

class LoginViewModelFactory(app: Application) : ViewModelProvider.Factory {
    private val session = SessionManager(app)
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        LoginViewModel(session) as T
}

class ProfileViewModelFactory(private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(app) as T
    }
}
