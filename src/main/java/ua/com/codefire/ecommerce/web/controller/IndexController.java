package ua.com.codefire.ecommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.codefire.ecommerce.data.entity.*;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.repo.*;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by human on 1/31/17.
 */
@Controller
public class IndexController {
    private static final List<String> productModels = new ArrayList<>(Arrays.asList("IPhone", "IPode",
            "IChair", "ITable", "IMac", "ITV", "SamsungPhone", "SamsungPode", "SamsungChair", "SamsungTable",
            "SamsungMac", "SamsungTV"));

    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;
    private final CurrencyRepo currencyRepo;
    private final PriceRepo priceRepo;
    private final ProductTypeRepo productTypeRepo;

    @Autowired
    public IndexController(ProductRepo productRepo, BrandRepo brandRepo, CurrencyRepo currencyRepo,
                           PriceRepo priceRepo, ProductTypeRepo productTypeRepo) {
        this.productRepo = productRepo;
        this.brandRepo = brandRepo;
        this.currencyRepo = currencyRepo;
        this.priceRepo = priceRepo;
        this.productTypeRepo = productTypeRepo;
    }

    @RequestMapping({"/", "/index"})
    public String getIndex(Model model) {
        List<Product> allProducts = productRepo.findAll();

        model.addAttribute("products", allProducts);

        return "index";
    }

    @PostConstruct
    public void postConstruct() {
        initBrands("Apple", "Samsung", "Sony", "Lenovo");
        initCurrencies("$", "€", "円", "￥");
        initProductTypes("Mobile", "Notebook", "Furniture");
        initPrices();
        initProducts();
    }

    private void initBrands(String... brands) {
        for (String brand : brands) {
            brandRepo.addBrand(new Brand(brand));
        }
    }

    private void initCurrencies(String... currencies) {
        for (String currency : currencies) {
            currencyRepo.add(new Currency(currency));
        }
    }

    private void initProductTypes(String... productTypes) {
        for (String productType : productTypes) {
            productTypeRepo.add(new ProductType(productType));
        }
    }

    private void initPrices() {
        List<Currency> currencies = currencyRepo.getAllCurrencies();
        List<Integer> currencyIds = currencies.stream()
                .map(Currency::getId)
                .collect(Collectors.toList());
        for(int i = 20; i < 100; i += 5) {
            int currencyId = ThreadLocalRandom.current().nextInt(Collections.min(currencyIds),
                    Collections.max(currencyIds) + 1);
            priceRepo.addPrice(
                    new Price((double)i,
                            Timestamp.valueOf(LocalDateTime.now()),
                            currencies.stream()
                                    .filter(article -> article.getId() == currencyId)
                                    .findFirst().get()));
        }
    }

    private void initProducts() {
        List<Price> prices = priceRepo.findAll();
        List<Brand> brands = brandRepo.findAll();
        List<ProductType> productTypes = productTypeRepo.findAll();

        prices.forEach(price -> {
            brands.forEach(brand -> {
                productTypes.forEach(productType -> {
                    productRepo.addProduct(new Product(price, productType,
                            brand, productModels.get((new Random()).nextInt(productModels.size()))));
                });
            });
        });
    }
}
