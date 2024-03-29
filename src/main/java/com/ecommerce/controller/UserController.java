package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.entity.Wishlist;
import com.ecommerce.repository.WishlistRepository;
import com.ecommerce.service.UserService;
import com.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private WishlistService wishlistService;
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
        List<Wishlist> wishlist = wishlistService.getWishlist(authentication);
        List<String> productList = new ArrayList<>();
        for(Wishlist wl : wishlist){
            productList.add(wl.getProduct().getName());
        }
//        for(Product product : productList){
//            System.out.println(product.getName());
//        }
        model.addAttribute("msg",productList);
        return "myWishlist";
    }

    @GetMapping("/addWishlist")
    public String addWishlist(Authentication authentication ,@RequestParam String product){
        userService.addWishlist(authentication,product);
        return "addWishlist";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDeniedPage";
    }
}
