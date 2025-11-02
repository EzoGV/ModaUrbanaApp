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
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.repository.ProductRepository

@Composable
fun CatalogScreen() {
    var selectedCategory by remember { mutableStateOf(Category.Polerones) }
    var order by remember { mutableStateOf(Order.Featured) }

    val products = remember(selectedCategory, order) {
        val base = ProductRepository.byCategory(selectedCategory)
        when (order) {
            Order.Featured -> base
            Order.PriceAsc -> base.sortedBy { it.price }
            Order.PriceDesc -> base.sortedByDescending { it.price }
        }
    }

    Column(Modifier.fillMaxSize().padding(12.dp)) {
        CategoryTabs(
            categories = ProductRepository.allCategories(),
            selected = selectedCategory,
            onSelect = { selectedCategory = it }
        )
        Spacer(Modifier.height(8.dp))
        FilterBar(order = order, onChange = { order = it })
        Spacer(Modifier.height(12.dp))
        ProductGrid(products)
    }
}

private enum class Order { Featured, PriceAsc, PriceDesc }

@Composable
private fun CategoryTabs(
    categories: List<Category>,
    selected: Category,
    onSelect: (Category) -> Unit
) {
    ScrollableTabRow(selectedTabIndex = categories.indexOf(selected)) {
        categories.forEach { c ->
            val index = categories.indexOf(c)
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
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        AssistChip(
            label = { Text("Destacados") },
            onClick = { onChange(Order.Featured) },
            leadingIcon = {},
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
private fun ProductGrid(products: List<com.example.modaurbanaapp.model.Product>) {
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

@Composable
private fun ProductCard(name: String, price: Int, oldPrice: Int?, imageUrl: String) {
    Card {
        Column {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(140.dp),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(10.dp)) {
                Text(name, maxLines = 2, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(4.dp))
                Text("$${price}", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
            }
        }
    }
}

