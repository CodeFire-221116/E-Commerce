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
import ua.com.codefire.ecommerce.data.service.ProductService;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by human on 1/31/17.
 */
@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @RequestMapping({"/", "/index"})
    public String getIndex(Model model) {
        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);

        return "products/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateProductPage(Model model) {

        Product productToEdit = new Product();
        productToEdit.setProductType(new ProductType());
        productToEdit.setCurrency(new Currency());
        productToEdit.setBrand(new Brand());

        model.addAttribute("productToEdit", productToEdit);
        model.addAttribute("currencies", productService.getAllCurrencies());
        model.addAttribute("types", productService.getAllProductTypes());
        model.addAttribute("brands", productService.getAllBrands());

        return "products/edit";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postCreateProduct(@Validated @ModelAttribute Product product, BindingResult result) {

        product.getBrand().setName(productService.getBrandNameById(product.getBrand().getId()));
        product.getCurrency().setName(productService.getCurrencyNameById(product.getCurrency().getId()));
        product.getProductType().setName(productService.getProductTypeNameById(product.getProductType().getId()));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        product.setLastUpdated(timestamp);

        if (!result.hasErrors())
            productService.createProduct(product);
        return "redirect:/";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getProductEditPage(@RequestParam int id, Model model) {

        model.addAttribute("productToEdit", productService.getProduct(id));
        model.addAttribute("currencies", productService.getAllCurrencies());
        model.addAttribute("types", productService.getAllProductTypes());
        model.addAttribute("brands", productService.getAllBrands());

        return "products/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postUpdateProduct(@Validated @ModelAttribute Product product, BindingResult result) {

        product.getBrand().setName(productService.getBrandNameById(product.getBrand().getId()));
        product.getCurrency().setName(productService.getCurrencyNameById(product.getCurrency().getId()));
        product.getProductType().setName(productService.getProductTypeNameById(product.getProductType().getId()));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        product.setLastUpdated(timestamp);

        if (!result.hasErrors())
            productService.updateProduct(product);

        return "redirect:/";
    }

    @RequestMapping(value = "/productType/addProduct", method = RequestMethod.POST)
    public String postAddType(@ModelAttribute ProductType productType) {

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
