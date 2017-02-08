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

    @RequestMapping({"/", "/index"})
    public String getIndex(Model model) {
        List<Product> allProducts = productService.getAllProducts();

        model.addAttribute("products", allProducts);

        return "products/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateProductPage(Model model) {

        Product productToEdit = new Product();
        productToEdit.setProductType(new ProductType());
        Price priceToEdit = new Price();
        priceToEdit.setLastUpdated(new Timestamp(System.currentTimeMillis()));
        priceToEdit.setCurrency(new Currency());
        productToEdit.setPrice(priceToEdit);
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
        product.getPrice().setLastUpdated(new Timestamp(System.currentTimeMillis()));
        product.getPrice().getCurrency().setName(priceService.getCurrencyNameById(product.getPrice().getCurrency().getId()));
        product.getPrice().setValue(Double.parseDouble(priceService.getPriceValueById(product.getPrice().getId())));

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
        product.getPrice().setLastUpdated(new Timestamp(System.currentTimeMillis()));
        product.getPrice().getCurrency().setName(priceService.getCurrencyNameById(product.getPrice().getCurrency().getId()));
        product.getPrice().setValue(Double.parseDouble(priceService.getPriceValueById(product.getPrice().getId())));

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
}
