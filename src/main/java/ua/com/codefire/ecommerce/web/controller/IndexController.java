package ua.com.codefire.ecommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ua.com.codefire.ecommerce.data.entity.*;
import ua.com.codefire.ecommerce.data.entity.Currency;
import ua.com.codefire.ecommerce.data.repo.*;
import ua.com.codefire.ecommerce.data.service.PriceService;
import ua.com.codefire.ecommerce.data.service.ProductService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
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

//    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
//    public String handleFileUpload(@RequestParam CommonsMultipartFile[] fileUpload) throws Exception {
//
//        if (fileUpload != null && fileUpload.length > 0) {
//            for (CommonsMultipartFile aFile : fileUpload) {
//                System.out.println("Saving file: " + aFile.getOriginalFilename());
//                Product product = new Product();
//                product.setPhoto(aFile.getBytes());
//                productService.updateProduct(product);
//            }
//        }
//        return "Success";
//    }

    @RequestMapping({"/", "/index"})
    public String getIndex(Model model) {
        List<Product> productsByPage = productService.getProductsByPage(1, amountByPage);
        model.addAttribute("products", productsByPage);
        model.addAttribute("totalProductsCount", productService.getProductsAmount());

        long totalProducts = productService.getProductsAmount();
        long remainder = totalProducts % amountByPage;
        model.addAttribute("numberOfPages", (int) (Math.ceil(totalProducts / amountByPage) + remainder / 10));

        return "products/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateProductPage(Model model) {

        Price topicalPrice = new Price();
        topicalPrice.setCurrency(new Currency());
        topicalPrice.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        topicalPrice.setProduct(new Product());
        topicalPrice.getProduct().setProductType(new ProductType());
        topicalPrice.getProduct().setBrand(new Brand());

        model.addAttribute("currencies", priceService.getAllCurrencies());
        model.addAttribute("types", productService.getAllProductTypes());
        model.addAttribute("brands", productService.getAllBrands());
        model.addAttribute("topicalPrice", topicalPrice);

        return "products/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateProduct(@Validated @ModelAttribute Price price, BindingResult result) {


        price.getProduct().getBrand().setName(productService.getBrandNameById(price.getProduct().getBrand().getId()));
        price.getProduct().getProductType().setName(productService.getProductTypeNameById(price.getProduct().getProductType().getId()));
        productService.createProduct(price.getProduct());
        price.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        price.setIsTopical(true);

        if (!result.hasErrors())
            priceService.createPrice(price);
        return "redirect:/";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getProductEditPage(@RequestParam int productId, Model model) {

        Price productToEditTopicalPrice = priceService.getTopicalPrice(productId);
        if (productToEditTopicalPrice.getProduct().getPhoto() != null) {

            byte[] photo64 = Base64.getEncoder().encode(productToEditTopicalPrice.getProduct().getPhoto());
            model.addAttribute("productImage", new String(photo64));

        }
        model.addAttribute("topicalPrice", productToEditTopicalPrice);
        //model.addAttribute("productToEdit", productToEditTopicalPrice.getProduct());
        model.addAttribute("currencies", priceService.getAllCurrencies());
        model.addAttribute("types", productService.getAllProductTypes());
        model.addAttribute("brands", productService.getAllBrands());

        model.addAttribute("productToEditPriceValue", productToEditTopicalPrice.getValue());

        return "products/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateProduct(@ModelAttribute Price price, @RequestParam CommonsMultipartFile[] fileUpload) {

        Price topicalPrice = priceService.getTopicalPrice(price.getProduct().getId());

        byte[] photo = topicalPrice.getProduct().getPhoto();

        if (topicalPrice.getValue() == price.getValue() && topicalPrice.getCurrency().getName().equals(price.getCurrency().getName())) {
            topicalPrice.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        } else {
            price.setId(null);
            topicalPrice.setIsTopical(false);
            priceService.createPrice(price);
        }

        priceService.updatePrice(topicalPrice);
        price.getCurrency().setName(priceService.getCurrencyNameById(price.getCurrency().getId()));
        price.getProduct().getBrand().setName(productService.getBrandNameById(price.getProduct().getBrand().getId()));
        price.getProduct().getProductType().setName(productService.getProductTypeNameById(price.getProduct().getProductType().getId()));

        if (fileUpload != null && fileUpload.length > 0) {
            for (CommonsMultipartFile aFile : fileUpload) {
                if(aFile.getSize() > 0) {
                    price.getProduct().setPhoto(aFile.getBytes());
                }
            }
            if (fileUpload[0].getSize() == 0){
                price.getProduct().setPhoto(photo);
            }
        }

        productService.updateProduct(price.getProduct());
        return "redirect:/";
    }

    @RequestMapping(value = "/productType/addProduct", method = RequestMethod.POST)
    public String postAddType(@ModelAttribute ProductType productType) {

        productType.setName(productService.getProductTypeNameById(productType.getId()));
        productService.createProductType(productType);

        return "products/edit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String postDeleteProduct(@RequestParam int productId) {

        productService.deleteProduct(productService.getProduct(productId));

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

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String initValues() {
        initBrands("Apple", "Samsung", "Sony", "Lenovo");
        initCurrencies("$", "€", "円", "￥");
        initProductTypes("Mobile", "Notebook", "Furniture");
        initPrices();
        initProducts();

        return "redirect:/";
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
        for (int i = 20; i < 100; i += 5) {
            int currencyId = ThreadLocalRandom.current().nextInt(Collections.min(currencyIds),
                    Collections.max(currencyIds) + 1);
            priceService.createPrice(
                    new Price((double) i,
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

    //    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index(@RequestParam Integer pageNumber, @RequestParam Integer amountPerPage, Model model) {
        List<Product> allProducts = productService.getProductsByPage(pageNumber, amountPerPage);
        model.addAttribute("products", allProducts);
        model.addAttribute("totalProductsCount", productService.getProductsAmount());

        long totalProducts = productService.getProductsAmount();
        long remainder = totalProducts % amountByPage;
        model.addAttribute("numberOfPages", (int) (Math.ceil(totalProducts / amountByPage) + remainder / 10));

        return "products/list";
    }
}
