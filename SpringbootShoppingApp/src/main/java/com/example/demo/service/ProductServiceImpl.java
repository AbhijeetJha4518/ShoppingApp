package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository repository;
	
	@Override
	public String addProduct(Product product) {
		Product product1=repository.save(product);
		if(product1!=null)
		return "Product Saved Successfully";
		else
			return"Failed to save Product";
	}
}
