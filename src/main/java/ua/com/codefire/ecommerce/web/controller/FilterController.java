package ua.com.codefire.ecommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.codefire.ecommerce.data.entity.ProductType;
import ua.com.codefire.ecommerce.data.service.ProductService;

import java.util.List;

/**
 * Created by Valery on 02.02.2017.
 */
@Controller public class FilterController {

    @Autowired
    private ProductService service;

    @RequestMapping("/filter")
    public String getFilter(Model model) {
        List<ProductType> productTypes = service.getAllProductTypes();

        model.addAttribute("product_types", productTypes);

        return "filter";
    }
}
