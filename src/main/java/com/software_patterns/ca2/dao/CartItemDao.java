package com.software_patterns.ca2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software_patterns.ca2.entity.CartItem;
import com.software_patterns.ca2.repository.CartItemRepository;

@Service
public class CartItemDao {
	
	@Autowired
	CartItemRepository repo;
	
	public void save(CartItem cartItem) {
		repo.save(cartItem);
	}
	
	public Optional<CartItem> findById(int id) {
		return repo.findById(id);
	}
	
	public void delete(CartItem product) {
		repo.delete(product);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	public List<CartItem> findAll() {
		return (List<CartItem>) repo.findAll();
	}
	
	public CartItem findByProductIdAndOrderIdAndUserEmail(int productId, int orderId, String userEmail) {
		return repo.findByProductIdAndOrderIdAndUserEmail(productId, orderId, userEmail);
	}
}
