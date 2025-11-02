package com.example.modaurbanaapp.repository

import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product

class LocalProductRepository : ProductRepository {

    private val all = listOf(
        Product("1","Polerón Spider® – Negro",73990,90000,"https://cdn-images.farfetch-contents.com/28/81/41/53/28814153_58019764_1000.jpg",Category.Polerones),
        Product("2","Polerón Maltese Cross – Rojo",44990,50000,"https://picsum.photos/id/101/800",Category.Polerones),
        Product("3","Cloud Ocean Washed Black Tee",14000,28000,"https://picsum.photos/id/102/800",Category.Poleras),
        Product("4","Denim Wide Leg Black",35990,null,"https://picsum.photos/id/103/800",Category.Pantalones),
        Product("5","Knit Logo Sweater Grey",55990,null,"https://picsum.photos/id/104/800",Category.Sweeter),
        Product("6","Gorra Logo Washed",12990,null,"https://picsum.photos/id/105/800",Category.Accesorios),
    )

    override suspend fun featured(): List<Product> = all.take(4)
    override suspend fun byCategory(category: Category): List<Product> =
        all.filter { it.category == category }

    override fun allCategories(): List<Category> = Category.entries
}
