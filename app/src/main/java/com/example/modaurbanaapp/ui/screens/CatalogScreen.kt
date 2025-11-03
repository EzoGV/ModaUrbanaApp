package com.example.modaurbanaapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product
import com.example.modaurbanaapp.repository.LocalProductRepository
import com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme
import com.example.modaurbanaapp.ViewModel.CatalogViewModel
import com.example.modaurbanaapp.ViewModel.CatalogViewModelFactory
import com.example.modaurbanaapp.ui.state.Order
import com.example.modaurbanaapp.ui.components.*

// 🧭 Pantalla principal del catálogo
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = viewModel(factory = CatalogViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Obtiene las categorías desde el repositorio local
    val categories = LocalProductRepository().allCategories()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 12.dp)
    ) {
        // 🔹 Tabs de categorías
        CategoryTabs(
            categories = categories,
            selected = categories.firstOrNull { it == uiState.selectedCategory } ?: categories.first(),
            onSelect = { viewModel.changeCategory(it) }
        )


        Spacer(Modifier.height(8.dp))

        // 🔹 Filtro de orden
        FilterBar(
            order = uiState.order,
            onChange = { viewModel.changeOrder(it) } // ✅ sin "order =" dentro
        )

        Spacer(Modifier.height(12.dp))

        // 🔹 Cuerpo principal
        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.error != null -> Text(text = "Error: ${uiState.error}")
            else -> ProductGrid(uiState.products)
        }
    }
}

enum class Order { Featured, PriceAsc, PriceDesc }

// 🔹 Tabs para seleccionar categorías
@Composable
private fun CategoryTabs(
    categories: List<Category>,
    selected: Category,
    onSelect: (Category) -> Unit
) {
    ScrollableTabRow(selectedTabIndex = categories.indexOf(selected)) {
        categories.forEach { c ->
            Tab(
                selected = c == selected,
                onClick = { onSelect(c) },
                text = { Text(c.name) }
            )
        }
    }
}

// 🔹 Barra de filtros (Destacados / Precio ↑ / Precio ↓)
@Composable
private fun FilterBar(order: Order, onChange: (Order) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        AssistChip(
            label = { Text("Destacados") },
            onClick = { onChange(Order.Featured) },
            enabled = order != Order.Featured
        )
        AssistChip(
            label = { Text("Precio ↑") },
            onClick = { onChange(Order.PriceAsc) },
            enabled = order != Order.PriceAsc
        )
        AssistChip(
            label = { Text("Precio ↓") },
            onClick = { onChange(Order.PriceDesc) },
            enabled = order != Order.PriceDesc
        )
    }
}

// 🔹 Grilla de productos
@Composable
private fun ProductGrid(products: List<Product>) {
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

// 🔹 Tarjeta individual de producto
@Composable
    fun ProductCard(
    name: String,
    price: Int,
    oldPrice: Int?,
    imageUrl: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column {
            // Imagen del producto
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )

            // Información
            Column(Modifier.padding(10.dp)) {

                // Nombre del producto
                Text(
                    text = name,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )

                Spacer(Modifier.height(4.dp))

                // Precio
                Text(
                    text = "$$price",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCatalogScreen() {
    com.example.modaurbanaapp.ui.theme.ModaUrbanaAppTheme {
        CatalogScreen()
    }
}

