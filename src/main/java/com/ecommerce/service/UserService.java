package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public String saveUser(User user){

        String passwd= user.getPassword();
        String encodedPassword = passwordEncoder.encode(passwd);

        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        return "User has been added";
    }



    public void addWishlist(Authentication authentication,String productname){
        Optional<Product> optionalProduct = productRepository.findByName(productname);
        Product product = optionalProduct.get();

        Optional<User> optionalUser = userRepository.findByUserName(authentication.getName());
        User user = optionalUser.get();

        if(product != null){
            wishlistService.addWishlist(user,product);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found !");
        else {
            User user1 = user.get();
            return new org.springframework.security.core.userdetails.User(
                    user1.getUserName(),
                    user1.getPassword(),
                    user1.getRole().stream()
                            .map(role-> new SimpleGrantedAuthority(role))
                            .collect(Collectors.toList())
            );
        }
    }
}
