package com.software_patterns.ca2.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.software_patterns.ca2.dao.ProductDao;
import com.software_patterns.ca2.entity.Product;

@Component
public class CategorySearchStrategy implements ProductSearchStrategy {
	
	@Autowired
	ProductDao productDao;

	@Override
	public List<Product> search(String term) {
		return productDao.findAllByCategoryContaining(term);
	}
}
