package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController //@Controller+@ResponseBody
@RequestMapping("/products") //http://localhost:2001/products
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService service;
    
    @PostMapping("/save")  //http://localhost:2001/products/save
    public String saveProduct(@RequestBody Product product) {
        logger.info("Received request to save product: {}", product);
        try {
            String response = service.addProduct(product);
            logger.info("Product saved successfully: {}");
            return response;
        } catch (Exception e) {
            logger.error("Error occurred while saving product: {}",e);
            return "Error saving product";
        }
    }
}