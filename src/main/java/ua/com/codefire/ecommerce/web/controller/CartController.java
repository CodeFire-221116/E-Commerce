package ua.com.codefire.ecommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.codefire.ecommerce.data.entity.Product;
import ua.com.codefire.ecommerce.data.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valery on 15.02.2017.
 */
@Controller
public class CartController
{

    @Autowired
    private ProductService productService;

    private Map<Product, Integer> getCartContent(HttpServletRequest request) {
        Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        request.getSession().setAttribute("cart", cart);

        return cart;
    }


    @RequestMapping("/cart")
    public String getCart(HttpServletRequest request, Model model) {
        model.addAttribute("cartContent", getCartContent(request));

        return "/products/cart";
    }

    @RequestMapping("/cart/add")
    public String addToCart(@RequestParam int productId, HttpServletRequest request, Model model) {
        Map<Product, Integer> cart = getCartContent(request);
        Product product = productService.getProduct(productId);

        int count = cart.getOrDefault(product, 0);
        cart.put(product, count + 1);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/cart/remove")
    public String removeFromCart(@RequestParam int productId, HttpServletRequest request, Model model) {
        Map<Product, Integer> cart = getCartContent(request);

        cart.remove(productService.getProduct(productId));

        return "redirect:/cart";
    }

}
