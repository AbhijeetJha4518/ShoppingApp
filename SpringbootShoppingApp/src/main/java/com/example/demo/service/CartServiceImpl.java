package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
	
	CartRepository repository;

	@Override
	public Cart addToCart(Cart cart) {
		return repository.save(cart);
	}

}
