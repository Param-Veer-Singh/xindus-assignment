package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.entity.Wishlist;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserRepository userRepository;
    public List<Wishlist> getWishlist(Authentication authentication){
        String username = authentication.getName();
        System.out.println(authentication.getName());
        return wishlistRepository.findByUser(userRepository.findByUserName(username).get());
    }


    public void addWishlist(User user, Product product) {
        Wishlist wishlistItem = new Wishlist();
        wishlistItem.setUser(user);
        wishlistItem.setProduct(product);
        wishlistRepository.save(wishlistItem);
    }
}
