package com.software_patterns.ca2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software_patterns.ca2.entity.Product;
import com.software_patterns.ca2.strategy.ManufacturerSortStrategy;
import com.software_patterns.ca2.strategy.NameSortStrategy;
import com.software_patterns.ca2.strategy.PriceSortStrategy;
import com.software_patterns.ca2.strategy.ProductSortStrategy;
import com.software_patterns.ca2.strategy.StockSortStrategy;

@Service
public class ProductSortService {
	
	@Autowired
	NameSortStrategy nameSortStrategy;
	
	@Autowired
	ManufacturerSortStrategy manufacturerSortStrategy;

	@Autowired
	PriceSortStrategy priceSortStrategy;
	
	@Autowired
	StockSortStrategy stockSortStrategy;
	
	public List<Product> sort(List<Product> products, String sortBy, String type) {
		ProductSortStrategy strategy = null;
		
		switch (sortBy) {
		case "name":
            strategy = nameSortStrategy;
            break;
        case "manufacturer":
            strategy = manufacturerSortStrategy;
            break;
        case "price":
            strategy = priceSortStrategy;
            break;
        case "stock":
        	strategy = stockSortStrategy;
        	break;
        default:
        	strategy = nameSortStrategy;
        	break;
		}
			
		
		return strategy.sort(products, type);
	}


}
