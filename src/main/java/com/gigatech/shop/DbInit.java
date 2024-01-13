package com.gigatech.shop;

import com.gigatech.shop.Repository.ItemRepository;
import com.gigatech.shop.model.Item;
import com.gigatech.shop.service.RecommendedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final RecommendedProductService recommendedProductService;


    @Autowired
    public DbInit(ItemRepository itemRepository, RecommendedProductService recommendedProductService) {
        this.itemRepository = itemRepository;
        this.recommendedProductService = recommendedProductService;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(List.of(
                new Item("MSI Nvidia RTX 4090 GAMING X TRIO", new BigDecimal("8999.00"), "Dzięki karcie graficznej MSI GeForce RTX 4090 GAMING X TRIO możesz grać na najwyższym poziomie nie rezygnując ze stylu.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/10/pr_2022_10_10_13_29_8_931_04.jpg", "graphics card"),
                new Item("Intel Core i9-13900KF", new BigDecimal("2849.00"), "Ciesz się najwyższym komfortem grania w każdej postaci, teraz i w przyszłości, dzięki innowacyjnej architekturze procesora Intel Core i9-13900KF", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/9/pr_2022_9_27_11_45_44_663_00.jpg", "processor"),
                new Item("AMD Ryzen 5 5600X", new BigDecimal("699.00"), "Jednostka AMD Ryzen 5 5600X posiada 6 rdzeni i 12 wątków, gotowych do pracy przy maksymalnym obciążeniu w grach i specjalistycznych aplikacjach.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2020/10/pr_2020_10_9_12_59_29_839_00.jpg", "processor"),
                new Item("NVIDIA GeForce GTX 1660 Super", new BigDecimal("1299.00"), "Graphics card for gaming", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/10/pr_2022_10_10_13_29_8_931_04.jpg", "graphics card"),
                new Item("AMD Ryzen 7 5800X", new BigDecimal("2399.00"), "High-performance processor", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/9/pr_2022_9_27_11_45_44_663_00.jpg", "processor"),
                new Item("Samsung 970 EVO Plus 1TB", new BigDecimal("699.00"), "Fast NVMe SSD", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2020/10/pr_2020_10_9_12_59_29_839_00.jpg", "disk"),
                new Item("Corsair Vengeance LPX 16GB RAM", new BigDecimal("399.00"), "High-speed DDR4 RAM", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/5/pr_2022_5_20_15_52_42_853_00.jpg", "ram")
        ));

        recommendedProductService.updateRecommendedProduct();
    }

}
