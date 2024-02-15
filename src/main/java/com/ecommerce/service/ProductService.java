package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String saveProduct(Product product){
        productRepository.save(product);
        return "Product added";
    }

    public String deleteProduct(Integer id){
        if (productRepository.findById(id).isPresent() == false){
            return "Product not avaialable";
        }
        productRepository.deleteById(id);
        return "Product deleted";
    }
}
