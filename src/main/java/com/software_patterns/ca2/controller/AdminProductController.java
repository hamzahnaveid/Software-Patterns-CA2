package com.software_patterns.ca2.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.software_patterns.ca2.dao.CategoryDao;
import com.software_patterns.ca2.dao.ProductDao;
import com.software_patterns.ca2.dto.ProductRequest;
import com.software_patterns.ca2.entity.Product;
import com.software_patterns.ca2.service.ProductSearchService;
import com.software_patterns.ca2.service.ProductSortService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductSearchService searchService;
	
	@Autowired
	ProductSortService sortService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add-product")
	@ResponseBody
	public ResponseEntity<Product> addProduct(@ModelAttribute ProductRequest request) throws IOException {
		Product product = new Product();
		
		product.setImage(request.getImage().getBytes());
		product.setCategory(categoryDao.findById(request.getCategoryId()).get());
		product.setName(request.getName());
		product.setManufacturer(request.getManufacturer());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());
		product.setDescription(request.getDescription());
		
		productDao.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-products")
	@ResponseBody
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<List<Product>>(productDao.findAll(), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductsByTermAndSort(@RequestParam String term, @RequestParam String sortBy, @RequestParam String type) {
		List<Product> products;
		
		if (term.isEmpty()) {
			products = productDao.findAll();
		}
		else {
			products = searchService.search(term);
		}
		
		products = sortService.sort(products, sortBy, type);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/delete-product/{productId}")
	@ResponseBody
	public ResponseEntity<Void> deleteProduct(@PathVariable int productId) {
		productDao.deleteById(productId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
