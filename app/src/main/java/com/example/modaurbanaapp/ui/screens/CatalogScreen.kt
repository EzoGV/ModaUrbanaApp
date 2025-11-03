package com.example.modaurbanaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product
import com.example.modaurbanaapp.repository.LocalProductRepository
import com.example.modaurbanaapp.ViewModel.CatalogViewModel
import com.example.modaurbanaapp.ViewModel.CatalogViewModelFactory
import com.example.modaurbanaapp.ui.state.Order


// ------ TIPOS LOCALES (sin importar Order externo para evitar conflictos) ------
enum class Order { Featured, PriceAsc, PriceDesc }

// ------------------- PANTALLA PRINCIPAL DEL CATÁLOGO -------------------
@Composable
fun CatalogScreen(
    viewModel: CatalogViewModel = viewModel(factory = CatalogViewModelFactory())
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val categories = LocalProductRepository().allCategories()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        // Tabs de categorías
        CategoryTabs(
            categories = categories,
            selected = categories.firstOrNull { it == uiState.selectedCategory } ?: categories.first(),
            onSelect = { viewModel.changeCategory(it) }
        )

        Spacer(Modifier.height(8.dp))

        // Filtro de orden
        FilterBar(
            order = uiState.order,
            onChange = { viewModel.changeOrder(it) }
        )

        Spacer(Modifier.height(12.dp))

        // Contenido principal
        when {
            uiState.isLoading -> CircularProgressIndicator()
            uiState.error != null -> Text(text = "Error: ${uiState.error}")
            else -> ProductGrid(uiState.products)
        }
    }
}

// ------------------- COMPONENTES DE UI -------------------
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

@Composable
private fun FilterBar(order: Order, onChange: (Order) -> Unit) {
    androidx.compose.foundation.layout.Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
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

@Composable
private fun ProductGrid(products: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
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

@Composable
private fun ProductCard(
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
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .fillMaxSize()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(10.dp)) {
                Text(
                    text = name,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(Modifier.height(4.dp))
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
