package com.software_patterns.ca2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.software_patterns.ca2.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
	List<Product> findAllByNameContaining(String name);
	
	List<Product> findAllByCategory_NameContaining(String category);
	
}
