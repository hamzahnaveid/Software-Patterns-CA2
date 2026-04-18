package com.software_patterns.ca2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.software_patterns.ca2.dao.CategoryDao;
import com.software_patterns.ca2.dao.ProductDao;
import com.software_patterns.ca2.entity.Product;
import com.software_patterns.ca2.service.ProductSearchService;
import com.software_patterns.ca2.service.ProductSortService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductSearchService searchService;
	
	@Autowired
	ProductSortService sortService;
	
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

}
