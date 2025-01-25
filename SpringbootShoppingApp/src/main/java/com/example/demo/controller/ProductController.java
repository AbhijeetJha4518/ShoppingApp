package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.exception.ProductNotFound;

import lombok.AllArgsConstructor;

@RestController // @Controller+@ResponseBody
@RequestMapping("/products") // http://localhost:2001/products
@AllArgsConstructor
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	// @Autowired
	ProductService service;

	@PostMapping("/save") // http://localhost:2001/products/save
	public String saveProduct(@Valid @RequestBody Product product, BindingResult result) {
		if (result.hasErrors()) {
			return result.getAllErrors().toString();
		}
		logger.info("Received request to save product: {}", product);
		String response = service.addProduct(product);
		logger.info("Product saved successfully: {}", product);
		return response;
	}

	@PutMapping("/update") // http://localhost:2001/products/update
	public Product updateProduct(@Valid @RequestBody Product product, BindingResult result) throws ProductNotFound {
		if (result.hasErrors()) {
			return null;
		}
		logger.info("Received request to update product: {}", product);
		Product response = service.updateProduct(product);
		logger.info("Product updated successfully: {}", product);
		return response;
	}

	@DeleteMapping("/delete/{id}") // http://localhost:2001/products/delete
	public String deleteProduct(@PathVariable("id") int productId) throws ProductNotFound {
		logger.info("Received request to delete product with ID: {}", productId);
		String response = service.deleteProduct(productId);
		logger.info("Product deleted successfully with ID: {}", productId);
		return response;
	}

	@GetMapping("/getById/{id}") // http://localhost:2001/products/getById
	public Product getProduct(@PathVariable("id") int productId) throws ProductNotFound {
		logger.info("Received request to find product with ID: {}", productId);
		Product response = service.getProductById(productId);
		logger.info("Product found successfully with ID: {}", productId);
		return response;
	}

	@GetMapping("/getAll") // http://localhost:2001/products/getAll
	public List<Product> getAllProducts() {
		logger.info("Received request to find all products");
		List<Product> response = service.getAllProducts();
		logger.info("All products found successfully");
		return response;
	}

	@GetMapping("/getBetween/{price1}/{price2}") // http://localhost:2001/products/getBetween
	public List<Product> getAllProductBetween(@PathVariable("price1") int initialPrice,
			@PathVariable("price2") int finalPrice) {
		logger.info("Received request to get products between: {} and {}", initialPrice, finalPrice);
		List<Product> response = service.getAllProductBetween(initialPrice, finalPrice);
		logger.info("Products found successfully between: {} and {}", initialPrice, finalPrice);
		return response;
	}

	@GetMapping("/byCategory/{category}") // http://localhost:2001/products/byCategory
	public List<Product> getProductByCategory(@PathVariable String category) throws ProductNotFound {
		logger.info("Received request to get products of category: {}", category);
		List<Product> response = service.getProductByCategory(category);
		if (response.isEmpty()) {
			throw new ProductNotFound("Product is not found with this category");
		}

		else
			logger.info("Products found successfully of category: {}", category);
		return response;
	}
}