package com.example.modaurbanaapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.modaurbanaapp.ViewModel.CartViewModel

@Composable
fun CartScreen(vm: CartViewModel = viewModel()) {
    val items = vm.items.collectAsState().value

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Carrito de Compras", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))

        if (items.isEmpty()) {
            Text("Tu carrito está vacío.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items) { it ->
                    Card {
                        Row(
                            Modifier.padding(12.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            AsyncImage(
                                model = it.product.imageUrl,
                                contentDescription = it.product.name,
                                modifier = Modifier.size(64.dp)
                            )
                            Column(Modifier.weight(1f)) {
                                Text(it.product.name, style = MaterialTheme.typography.bodyLarge)
                                Text("$${it.product.price} x ${it.quantity}")
                            }
                            Row {
                                TextButton(onClick = { vm.removeOne(it.product) }) { Text("-") }
                                TextButton(onClick = { vm.add(it.product) }) { Text("+") }
                            }
                        }
                    }
                }
            }
            Divider()
            Row(Modifier.padding(top = 12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total: $${vm.total()}", style = MaterialTheme.typography.titleMedium)
                Button(onClick = vm::clear) { Text("Vaciar") }
            }
        }
    }
}
