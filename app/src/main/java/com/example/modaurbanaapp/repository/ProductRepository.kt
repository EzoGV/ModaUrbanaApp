package com.example.modaurbanaapp.repository


import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product

object ProductRepository {
    private val all = listOf(
        Product("1","Polerón Heavyweight® On God – Negro",53990,60000,"https://picsum.photos/id/100/800",Category.Polerones),
        Product("2","Polerón Maltese Cross – Rojo",44990,50000,"https://picsum.photos/id/101/800",Category.Polerones),
        Product("3","Cloud Ocean Washed Black Tee",14000,28000,"https://picsum.photos/id/102/800",Category.Poleras),
        Product("4","Denim Wide Leg Black",35990,null,"https://picsum.photos/id/103/800",Category.Pantalones),
        Product("5","Knit Logo Sweater Grey",55990,null,"https://picsum.photos/id/104/800",Category.Sweeter),
        Product("6","Gorra Logo Washed",12990,null,"https://picsum.photos/id/105/800",Category.Accesorios),
    )

    fun featured(): List<Product> = all.take(4)
    fun byCategory(category: Category): List<Product> = all.filter { it.category == category }
    fun allCategories() = Category.entries
}
