package ua.com.codefire.ecommerce.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.codefire.ecommerce.data.entity.Product;
import ua.com.codefire.ecommerce.data.service.ProductService;

import java.util.List;

/**
 * Created by human on 2/14/17.
 */
@RestController
public class ProductsRestController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/rest/products/all")
    public List<Product> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();

        System.out.println(allProducts.size());

        return allProducts;
    }

}
