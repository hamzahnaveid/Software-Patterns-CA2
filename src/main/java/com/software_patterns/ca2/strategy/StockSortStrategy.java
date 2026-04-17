package com.software_patterns.ca2.strategy;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.software_patterns.ca2.entity.Product;

@Component
public class StockSortStrategy implements ProductSortStrategy {
	
	@Override
	public List<Product> sort(List<Product> products, String type) {
		Comparator<Product> comparator = Comparator.comparing(Product::getStock);
		
		if (type.equals("desc")) {
			comparator = comparator.reversed();
		}
		
		return products.stream().sorted(comparator).toList();

	}

}
