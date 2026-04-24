package com.example.ecom.Controller;

import com.example.ecom.Model.Product;
import com.example.ecom.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}
