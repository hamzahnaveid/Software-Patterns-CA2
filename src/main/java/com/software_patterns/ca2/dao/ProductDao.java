package com.software_patterns.ca2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software_patterns.ca2.entity.Product;
import com.software_patterns.ca2.repository.ProductRepository;

@Service
public class ProductDao {
	
	@Autowired
	ProductRepository repo;
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public Optional<Product> findById(int id) {
		return repo.findById(id);
	}
	
	public void delete(Product product) {
		repo.delete(product);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	public List<Product> findAll() {
		return (List<Product>) repo.findAll();
	}
}
