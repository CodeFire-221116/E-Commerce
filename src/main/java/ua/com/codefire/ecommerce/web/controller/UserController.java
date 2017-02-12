package ua.com.codefire.ecommerce.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.codefire.ecommerce.data.entity.User;
import ua.com.codefire.ecommerce.data.service.UserService;

import java.util.List;

/**
 * Created by ankys on 11.02.2017.
 */
@RequestMapping("/users")
@Controller
public class UserController {
    @Autowired
    UserService userService;

    private static final int amountByPage = 20;

    @RequestMapping(value = {"/", "/index", "/list"}, method = RequestMethod.GET)
    public String postListUsers(Model model) {
        List<User> usersByPage = userService.getProductsByPage(1, amountByPage);
        long totalUsers = userService.getAmountOfEntities();

        model.addAttribute("users", usersByPage);
        model.addAttribute("totalUsersCount", totalUsers);

        long remainder = totalUsers % amountByPage;
        model.addAttribute("numberOfPages", (int)(Math.ceil(totalUsers / amountByPage) + remainder / 10));

        return "users/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddUser() {
        return "users/edit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAddUser() {
        return "users/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditUser() {
        return "users/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String postEditUser() {
        return "users/list";
    }
}
