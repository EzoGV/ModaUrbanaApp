package com.example.modaurbanaapp.repository

import com.example.modaurbanaapp.model.Category
import com.example.modaurbanaapp.model.Product

class LocalProductRepository : ProductRepository  {
    private val all = listOf(
        Product(1,"Polerón Spider® – Negro",73990,90000,"https://images.stockx.com/images/vertical/Sp5der-Og-Web-Hoodie-Black_1.jpg?fit=fill&bg=FFFFFF&w=396&h=504&q=60&dpr=1",Category.Polerones),
        Product(2,"Adidas x Thug Club Teamgeist Hooded Zip Up – Rojo",91990,150000,"https://lustmexico.com/cdn/shop/files/KC2210_3.png?v=1760996184",Category.Polerones),
        Product(3,"Jordan Boxy T-shirt Black",14000,28000,"https://beeway.cl/cdn/shop/files/C7A6B1B9-CF09-43FC-85A1-2083BB392EE4.png?v=1742079001&width=713",Category.Poleras),
        Product(4,"Heavyweight tee Stodak Black ",20000,28000,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrliRhqdFHX08DYvO5kK5Z0WK082k-d71mpQ&s",Category.Poleras),
        Product(5,"Black Baggy Jeans",35990,50990,"https://techwearstorm.com/cdn/shop/files/black-baggy-jeans-y2k-kanazawa-techwear-storm-466.jpg?v=1722850454",Category.Pantalones),
        Product(6,"Balenciaga Baggy Jeans",75990,90990,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWPBlSRWqBVvaW3BXvresIx-jFCxBohPhD_Q&s",Category.Pantalones),
        Product(7,"Raf Tribal Black",55990,81990,"https://cdnx.jumpseller.com/kuroarchives/image/41674700/thumb/1500/1500?1711506654",Category.Sweeter),
        Product(8,"Master Navy Knit Sweater",65990,83990,"https://emestudios.com/cdn/shop/files/MASTER_NAVY_KNIT_SWEATER_1_97910c81-dead-4cbb-8f1c-bceed7274114.webp?v=1756678940",Category.Sweeter),
        Product(9,"Gorra YVL",12990,null,"https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fimage%2F2025%2F05%2F12%2Fplayboi-carti-yvl-hat-brand-launch-young-vamp-life-release-info-004.jpg?q=75&w=800&cbr=1&fit=max",Category.Accesorios),
        Product(10,"Homer - Chain",98990,null,"https://www.homer.com/cdn/shop/files/Homer_Bullet_Hole_Pendant_with_Screws_M_Clover-Cody_Green_With_Chain__2048.png?v=1751551293&width=800",Category.Accesorios),
    )

    override suspend fun featured(): List<Product> = all.take(4)
    override suspend fun byCategory(category: Category): List<Product> =
        all.filter { it.category == category }

    override fun allCategories(): List<Category> = Category.entries
}
