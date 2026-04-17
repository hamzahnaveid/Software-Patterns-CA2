package com.software_patterns.ca2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.software_patterns.ca2.dao.CategoryDao;
import com.software_patterns.ca2.dto.CategoryRequest;
import com.software_patterns.ca2.entity.Category;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminCategoryController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add-category")
	@ResponseBody
	public ResponseEntity<Category> addCategory(@RequestBody CategoryRequest request) {
		Category category = new Category();
		category.setName(request.getName());
		category.setDescription(request.getDescription());
		
		categoryDao.save(category);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-categories")
	@ResponseBody
	public ResponseEntity<List<Category>> getCategories() {
		return new ResponseEntity<List<Category>>(categoryDao.findAll(), HttpStatus.OK);
	}
	
}
