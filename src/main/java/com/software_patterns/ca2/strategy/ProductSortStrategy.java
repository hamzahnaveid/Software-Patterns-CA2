package com.software_patterns.ca2.strategy;

import java.util.List;

import com.software_patterns.ca2.entity.Product;

public interface ProductSortStrategy {
	
	List<Product> sort(List<Product> products, String type);

}
