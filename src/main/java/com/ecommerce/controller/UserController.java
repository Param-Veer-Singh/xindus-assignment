package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/addUser")
    public String addUser(){
        return "signUp";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model){
        userService.saveUser(user);
        model.addAttribute("msg", "User added");
        return "signUp";
    }
    @GetMapping("/welcome")
    public String welcome(){
        return "welcomePage";
    }


    @GetMapping("/myWishlist")
    public String getWishlist(Authentication authentication, Model model){
        List<Product> wishlist = userService.getWishlist(authentication);
        model.addAttribute("msg",wishlist);
        return "myWishlist";
    }

    @GetMapping("/addWishlist")
    public String addWishlist(Authentication authentication ,@RequestParam String product){
        userService.addWishlist(authentication,product);
        return "addWishlist";
    }
    @GetMapping("/home")
    public String getHomePage() {
        return "homePage";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "adminPage";
    }

    @GetMapping("/user")
    public String getEmployeePage() {
        return "userPage";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDeniedPage";
    }
}
