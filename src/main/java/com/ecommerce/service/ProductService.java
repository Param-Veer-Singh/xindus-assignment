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
        productRepository.deleteById(id);
        return "Product deleted";
    }
}
