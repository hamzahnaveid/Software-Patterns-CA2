package com.software_patterns.ca2.strategy;

import java.util.List;

import com.software_patterns.ca2.entity.Product;

public interface ProductSearchStrategy {
	List<Product> search(String term);
}
