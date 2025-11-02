package com.example.modaurbanaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.modaurbanaapp.repository.ProductRepository
import com.example.modaurbanaapp.ui.components.ProductCard




@Composable
fun HomeScreen() {
    val products = ProductRepository.featured()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 🔹 Banner principal
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            AsyncImage(
                model = "https://picsum.photos/1200/400",
                contentDescription = "Banner principal",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        // 🔹 Título de sección
        Text(
            text = "Más Vendidos",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        // 🔹 Grid de productos
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(products) { p ->
                ProductCard(
                    name = p.name,
                    price = p.price,
                    oldPrice = p.oldPrice,
                    imageUrl = p.imageUrl
                )
            }
        }
    }
}
