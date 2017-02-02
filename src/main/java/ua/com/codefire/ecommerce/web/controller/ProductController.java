package ua.com.codefire.ecommerce.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.codefire.ecommerce.data.entity.Product;

import javax.enterprise.inject.Model;

/**
 * Created by ankys on 02.02.2017.
 */
@Controller
@RequestMapping(path = "/products")
public class ProductController {
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addProductToBasket(Model model, @Validated @ModelAttribute Product product, BindingResult result) {
        if(!result.hasErrors())
        {

        }
    }
}
