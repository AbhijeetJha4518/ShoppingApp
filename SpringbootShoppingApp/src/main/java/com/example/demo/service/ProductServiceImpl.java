package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFound;
import com.example.demo.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	ProductRepository repository;

	@Override
	public String addProduct(Product product) {
		Product product1 = repository.save(product);
		if (product1 != null)
			return "Product Saved Successfully";
		else
			return "Failed to save Product";
	}

	@Override
	public Product updateProduct(Product product) {
		return repository.save(product);

	}

	@Override
	public String deleteProduct(int productId) {
		repository.deleteById(productId);
		return "Product Deleted Susseccfully";
	}

	@Override
	public Product getProductById(int productId) throws ProductNotFound {
		Optional<Product> optional=repository.findById(productId); //findById provide optional product to avoid null point error
		if(optional.isPresent())
		return optional.get();
		else
			throw new ProductNotFound("No Product Found with given ID!!");
	}

	@Override
	public List<Product> getAllProducts() {

		return repository.findAll();
	}

	@Override
	public List<Product> getAllProductBetween(int initialPrice, int finalPrice) {
		return repository.findByProductPriceBetween(initialPrice, finalPrice);

	}
	
	@Override
	public List<Product> getProductByCategory(String productCategory) {
		return repository.findByProductCategory(productCategory);

	}

}
