package ua.com.codefire.ecommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.codefire.ecommerce.data.entity.*;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.repo.*;
import ua.com.codefire.ecommerce.data.service.PriceService;
import ua.com.codefire.ecommerce.data.service.ProductService;

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
    @Autowired
    private ProductService productService;
    @Autowired
    private PriceService priceService;

    private static final List<String> productModels = new ArrayList<>(Arrays.asList("IPhone", "IPode",
            "IChair", "ITable", "IMac", "ITV", "SamsungPhone", "SamsungPode", "SamsungChair", "SamsungTable",
            "SamsungMac", "SamsungTV"));

    public static final int amountByPage = 20;

    @RequestMapping({"/", "/index"})
    public String getIndex(Model model) {
        List<Product> productsByPage = productService.getProductsByPage(1, amountByPage);
        model.addAttribute("products", productsByPage);

        long totalProducts = productService.getProductsAmount();
        long remainder = totalProducts % amountByPage;
        model.addAttribute("numberOfPages", Math.ceil(totalProducts / amountByPage) + remainder / 10);

        return "products/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateProductPage(Model model) {

        Product productToEdit = new Product();
        productToEdit.setProductType(new ProductType());
        Price priceToEdit = new Price();
        priceToEdit.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        priceToEdit.setCurrency(new Currency());
        productToEdit.setBrand(new Brand());

        model.addAttribute("productToEdit", productToEdit);
        model.addAttribute("currencies", priceService.getAllCurrencies());
        model.addAttribute("types", productService.getAllProductTypes());
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("prices", priceService.getAllPrices());

        return "products/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateProduct(@Validated @ModelAttribute Product product, BindingResult result) {

        product.getBrand().setName(productService.getBrandNameById(product.getBrand().getId()));
        product.getProductType().setName(productService.getProductTypeNameById(product.getProductType().getId()));

        if (!result.hasErrors())
            productService.createProduct(product);
        return "redirect:/";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getProductEditPage(@RequestParam int id, Model model) {

        model.addAttribute("productToEdit", productService.getProduct(id));
        model.addAttribute("currencies", priceService.getAllCurrencies());
        model.addAttribute("types", productService.getAllProductTypes());
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("prices", priceService.getAllPrices());

        return "products/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateProduct(@ModelAttribute Product product) {

        product.getBrand().setName(productService.getBrandNameById(product.getBrand().getId()));
        product.getProductType().setName(productService.getProductTypeNameById(product.getProductType().getId()));

        productService.updateProduct(product);
        return "redirect:/";
    }

    @RequestMapping(value = "/productType/addProduct", method = RequestMethod.POST)
    public String postAddType(@ModelAttribute ProductType productType){

        productType.setName(productService.getProductTypeNameById(productType.getId()));
        productService.createProductType(productType);

        return "products/edit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteProduct(@RequestParam int id) {

        productService.deleteProduct(productService.getProduct(id));

        return "redirect:/";
    }

    @PostConstruct
    public void postConstruct() {
//        initBrands("Apple", "Samsung", "Sony", "Lenovo");
//        initCurrencies("$", "€", "円", "￥");
//        initProductTypes("Mobile", "Notebook", "Furniture");
//        initPrices();
//        initProducts();
    }

    private void initBrands(String... brands) {
        for (String brand : brands) {
            productService.createBrand(new Brand(brand));
        }
    }

    private void initCurrencies(String... currencies) {
        for (String currency : currencies) {
            priceService.createCurrency(new Currency(currency));
        }
    }

    private void initProductTypes(String... productTypes) {
        for (String productType : productTypes) {
            productService.createProductType(new ProductType(productType));
        }
    }

    private void initPrices() {
        List<Currency> currencies = priceService.getAllCurrencies();
        List<Integer> currencyIds = currencies.stream()
                .map(Currency::getId)
                .collect(Collectors.toList());
        for(int i = 20; i < 100; i += 5) {
            int currencyId = ThreadLocalRandom.current().nextInt(Collections.min(currencyIds),
                    Collections.max(currencyIds) + 1);
            priceService.createPrice(
                    new Price((double)i,
                            Timestamp.valueOf(LocalDateTime.now()),
                            currencies.stream()
                                    .filter(article -> article.getId() == currencyId)
                                    .findFirst().get()));
        }
    }

    private void initProducts() {
        List<Price> prices = priceService.getAllPrices();
        List<Brand> brands = productService.getAllBrands();
        List<ProductType> productTypes = productService.getAllProductTypes();

        prices.forEach(price -> {
            brands.forEach(brand -> {
                productTypes.forEach(productType -> {
                    productService.createProduct(new Product(productType,
                            brand, productModels.get((new Random()).nextInt(productModels.size()))));
                });
            });
        });
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index(@RequestParam Integer pageNumber, @RequestParam Integer amountPerPage, Model model) {
        List<Product> allProducts = productService.getProductsByPage(pageNumber, amountPerPage);
        model.addAttribute("products", allProducts);

        long totalProducts = productService.getProductsAmount();
        long remainder = totalProducts % amountByPage;
        model.addAttribute("numberOfPages", Math.ceil(totalProducts / amountByPage) + remainder / 10);

        return "products/list";
    }
}
