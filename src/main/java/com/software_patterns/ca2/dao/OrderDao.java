package com.software_patterns.ca2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software_patterns.ca2.entity.Order;
import com.software_patterns.ca2.repository.OrderRepository;

@Service
public class OrderDao {
	
	@Autowired
	OrderRepository repo;
	
	public void save(Order order) {
		repo.save(order);
	}
	
	public Optional<Order> findById(int id) {
		return repo.findById(id);
	}
	
	public void delete(Order order) {
		repo.delete(order);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	public List<Order> findAll() {
		return (List<Order>) repo.findAll();
	}
	
	public List<Order> findAllByUserEmail(String email) {
		return (List<Order>) repo.findAllByUserEmail(email);
	}
	
	public Order findByUserEmailAndStatus(String email, String status) {
		return repo.findByUserEmailAndOrderStatus(email, status);
	}
	
}
