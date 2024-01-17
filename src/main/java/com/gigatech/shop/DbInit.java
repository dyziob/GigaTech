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
                new Item("Gigabyte GeForce RTX 3060 GAMING OC LHR 12GB GDDR6", new BigDecimal("1449.00"), "Zyskaj gamingową wydajność, na jaką zasługujesz.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2021/6/pr_2021_6_15_13_24_23_26_05.jpg", "graphics card"),
                new Item("XFX Radeon RX 7900 XT Gaming SPEEDSTER MERC310 20GB GDDR6", new BigDecimal("3789.00"),"Korzystaj z wysokiej wydajności architektury AMD RDNA 3 z kartą graficzną XFX Radeon RX 7900 XT Gaming SPEEDSTER MERC310 20 GB GDDR6", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/12/pr_2022_12_14_14_40_32_581_00.jpg", "graphics card"),
                new Item("Kingston FURY 32GB (2x16GB) 6000MHz CL36 Beast Black EXPO AMD", new BigDecimal("569.00"), "Pamięć Kingston FURY™ Beast DDR5 oferuje najnowszą i najbardziej zaawansowaną technologię dla platform do gier nowej generacji.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/9/pr_2022_9_13_13_19_33_333_00.jpg", "ram"),
                new Item("Corsair 32GB (2x16GB) 7200MHz CL34 Vengeance RGB", new BigDecimal("779.00"), "Przełam bariery swojego systemu dzięki innej niż dotychczasowe pamięci DDR5. ", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/12/pr_2022_12_21_6_49_51_200_01.jpg", "ram"),
                new Item("Lexar 4TB M.2 PCIe Gen4 NVMe NM790", new BigDecimal("1099.99"), "Poznaj dyski Lexar NM790, nowoczesne jednostki SSD wyposażone w interfejs PCIe Gen4 NVMe, dzięki którym osiągniesz najwyższą wydajność i szybkość.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2023/5/pr_2023_5_24_11_14_38_882_03.jpg", "disk"),
                new Item("Samsung 1TB M.2 PCIe Gen4 NVMe 980 PRO", new BigDecimal("419.00"), "Uwolnij moc dysku PCIe 4.0 NVMe SSD 980 PRO firmy Samsung, by cieszyć się nową jakością pracy komputera.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2020/9/pr_2020_9_23_13_23_42_498_00.jpg", "disk"),
                new Item("AMD Ryzen 9 7900X3D", new BigDecimal("2199.00"), "Łącząc technologię AMD 3D V-Cache z procesorami Ryzen serii 7000X3D, zyskujesz ogromny wzrost wydajności w grach.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2023/2/pr_2023_2_24_14_19_6_20_00.jpg", "processor"),
                new Item("Intel Core i9-14900KF", new BigDecimal("2599.00"), "Dzięki lepszemu zarządzaniu energią, wydajności wspomaganej sztuczną inteligencją i najlepszym specyfikacjom.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2023/10/pr_2023_10_12_9_1_5_805_00.jpg", "processor"),
                new Item("Gigabyte GeForce RTX 4060 Eagle OC 8GB GDDR6", new BigDecimal("1469.00"), "Dzięki mocy obliczeniowej układów GeForce RTX z serii 40 możesz cieszyć się niezwykłą szczegółowością wirtualnych światów.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2023/6/pr_2023_6_28_12_18_59_269_00.jpg", "graphics card"),
                new Item("MSI GeForce RTX 4070 GAMING X SLIM 12GB GDDR6X", new BigDecimal("3299.00"), "Architektura NVIDIA Ada Lovelace wyprzedza swój czas, dzięki czemu wyzwolisz pełną potęgę ray tracingu.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2023/8/pr_2023_8_28_12_36_35_874_00.jpg", "graphics card"),
                new Item("Gigabyte Radeon RX 6600 EAGLE 8GB GDDR6", new BigDecimal("999.00"), "Wybierz wydajność bez zbędnych fajerwerków. Gigabyte Radeon RX 6600 EAGLE 8GB GDDR6 to wydajna konstrukcja wykorzystująca architekturę AMD RDNA 2, by zapewnić Ci płynną rozgrywkę.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2021/10/pr_2021_10_13_8_50_59_821_00.jpg", "graphics card"),
                new Item("AMD Ryzen Threadripper PRO 5995WX", new BigDecimal("27999.00"),  "Poznaj nową serię ultrawydajnych procesorów Ryzen Threadripper PRO od AMD. Stworzony, aby zapewnić przewagę na rynku jednostek do profesjonalnych zastosowań.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/8/pr_2022_8_18_13_35_58_215_01.jpg", "processor"),
                new Item("Intel Core i7-12700K", new BigDecimal("1499.00"), "Poznaj procesory Intel Core dwunastej generacji do komputerów stacjonarnych, stworzone do gamingu nowej generacji.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2021/10/pr_2021_10_26_7_35_16_132_00.jpg", "processor"),new Item("Intel Core i5-13600KF", new BigDecimal("1349.00"), "Od wieloosobowych konfrontacji po epickie przygody — bądź bliżej akcji niż kiedykolwiek wcześniej dzięki innowacyjnej architekturze procesora Intel Core i5-13600KF.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/9/pr_2022_9_27_12_11_20_160_02.jpg", "processor"),
                new Item("AMD Ryzen 7 5800X3D", new BigDecimal("1349.00"), "Gamingową jednostkę AMD Ryzen 7 5800X3D wyposażono w 8 rdzeni i 16 wątków, gotowych do pracy przy maksymalnym obciążeniu w grach i specjalistycznych aplikacjach.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/4/pr_2022_4_4_12_25_58_758_01.jpg", "processor"),
                new Item("Corsair 32GB (2x16GB) 3600MHz CL18 Vengeance RGB PRO", new BigDecimal("419.00"), "Przetaktowana pamięć Corsair Vengeance Pro RGB to najwyższa przepustowość i krótki czas reakcji.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2022/4/pr_2022_4_5_13_3_10_510_00.jpg", "ram"),
                new Item("Patriot 32GB (2x16GB) 3600MHz CL18 Viper Steel", new BigDecimal("339.00"), "Niezależnie od tego, czy korzystasz z platformy Intel lub AMD, Twoja konfiguracja potrzebuje najlepszej i najszybszej pamięci RAM.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/2/pr_2019_2_1_13_8_17_828_00.jpg", "ram"),
                new Item("Seagate BARRACUDA 4TB 5400obr. 256MB", new BigDecimal("465.00"), "Uniwersalny dysk Seagate Barracuda to gwarancja sprawnego dostępu do Twoich plików. Wykorzystaj jego przestrzeń do przechowywania danych niezbędnych do pracy i zyskaj wygodne narzędzie.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2019/9/pr_2019_9_10_9_47_8_290_03.jpg", "disk"),
                new Item("GOODRAM 1TB M.2 PCIe Gen4 NVMe PX700", new BigDecimal("319.00"), "GOODRAM PX700 to nowoczesny dysk SSD M.2 o pojemności 1 TB, który wykorzystuje maksymalne możliwości interfejsu PCIe 4 x4.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2023/11/pr_2023_11_21_11_15_41_315_00.jpg", "disk"),
                new Item("MSI 1TB M.2 PCIe NVMe Spatium M371", new BigDecimal("229.00"), "Pamięci SPATIUM skonstruowano w celu rozszerzenia oferty MSI o kategorię wysokowydajnych pamięci masowych.", "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2023/1/pr_2023_1_11_12_40_5_808_00.jpg", "disk")
        ));

        recommendedProductService.updateRecommendedProduct();
    }

}
