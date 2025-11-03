package com.example.modaurbanaapp.repository

import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product

class LocalProductRepository : ProductRepository  {
    private val all = listOf(
        Product("1","Polerón Spider® – Negro",73990,90000,"https://cdn-images.farfetch-contents.com/28/81/41/53/28814153_58019764_1000.jpg",Category.Polerones),
        Product("2","Adidas x Thug Club Teamgeist Hooded Zip Up – Rojo",91990,150000,"https://lustmexico.com/cdn/shop/files/KC2210_3.png?v=1760996184",Category.Polerones),
        Product("3","Jordan Boxy T-shirt Black",14000,28000,"https://beeway.cl/cdn/shop/files/C7A6B1B9-CF09-43FC-85A1-2083BB392EE4.png?v=1742079001&width=713",Category.Poleras),
        Product("4","Black Baggy Jeans",35990,50990,"https://techwearstorm.com/cdn/shop/files/black-baggy-jeans-y2k-kanazawa-techwear-storm-466.jpg?v=1722850454",Category.Pantalones),
        Product("5","Raf Tribal Black",55990,81990,"https://cdnx.jumpseller.com/kuroarchives/image/41674700/thumb/1500/1500?1711506654",Category.Sweeter),
        Product("6","Gorra YVL",12990,null,"https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2025%2F05%2F12%2Fplayboi-carti-yvl-hat-brand-launch-young-vamp-life-release-info-004.jpg?q=75&w=800&cbr=1&fit=max",Category.Accesorios),
    )

    override suspend fun featured(): List<Product> = all.take(4)
    override suspend fun byCategory(category: Category): List<Product> =
        all.filter { it.category == category }

    override fun allCategories(): List<Category> = Category.entries
}
