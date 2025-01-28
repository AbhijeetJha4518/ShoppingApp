package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cart;
import com.example.demo.service.CartService;

import lombok.AllArgsConstructor;

@RestController // @Controller+@ResponseBody
@RequestMapping("/cart") // http://localhost:2001/products
@AllArgsConstructor
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	// @Autowired
	CartService service;

	@PostMapping("/save") // http://localhost:2001/products/save
	public Cart addToCart (@RequestBody Cart cart) {
		logger.info("Product saved successfully: {}", cart);
		return service.addToCart(cart);

	}
}