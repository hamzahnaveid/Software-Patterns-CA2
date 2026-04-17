package com.software_patterns.ca2.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software_patterns.ca2.entity.Product;
import com.software_patterns.ca2.strategy.CategorySearchStrategy;
import com.software_patterns.ca2.strategy.NameSearchStrategy;

@Service
public class ProductSearchService {
	
	@Autowired
	NameSearchStrategy nameStrategy;
	
	@Autowired
	CategorySearchStrategy categoryStrategy;
	
	public List<Product> search(String term) {
		Set<Product> resultSet = new HashSet<Product>();
		
		resultSet.addAll(nameStrategy.search(term));
		resultSet.addAll(categoryStrategy.search(term));
		
		return new ArrayList<Product>(resultSet);
	}

}
