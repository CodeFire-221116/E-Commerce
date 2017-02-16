package ua.com.codefire.ecommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.com.codefire.ecommerce.data.entity.ShoppingCart;
import ua.com.codefire.ecommerce.data.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Valery on 15.02.2017.
 */
@Controller
public class CartController
{

    @Autowired
    private ProductService productService;

    private ShoppingCart getCartContent() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return getCartContent(attr.getRequest());
    }

    private ShoppingCart getCartContent(HttpServletRequest request) {
        HttpSession session = request.getSession();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }

        return shoppingCart;
    }


    @RequestMapping("/cart")
    public String getCart(Model model) {
        model.addAttribute("shoppingCart", getCartContent());

        return "/products/cart";
    }

    @RequestMapping("/cart/add")
    public String addToCart(@RequestParam int productId, HttpServletRequest request, Model model) {

        getCartContent(request).addProductToCart(productService.getProduct(productId));

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping("/cart/remove")
    public String removeFromCart(@RequestParam int productId, Model model) {
        getCartContent().removeProductFromCart(productService.getProduct(productId));

        return "redirect:/cart";
    }

}
