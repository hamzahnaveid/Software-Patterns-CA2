package com.software_patterns.ca2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software_patterns.ca2.entity.Category;
import com.software_patterns.ca2.repository.CategoryRepository;

@Service
public class CategoryDao {
	
	@Autowired
	private CategoryRepository repo;
	
	public void save(Category category) {
		repo.save(category);
	}
	
	public Optional<Category> findById(int id) {
		return repo.findById(id);
	}
	
	public void delete(Category category) {
		repo.delete(category);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	public List<Category> findAll() {
		return (List<Category>) repo.findAll();
	}

}
