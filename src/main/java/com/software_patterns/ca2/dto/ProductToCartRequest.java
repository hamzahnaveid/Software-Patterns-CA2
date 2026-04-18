package com.software_patterns.ca2.dto;

import lombok.Data;

@Data
public class ProductToCartRequest {
	
	private String userEmail;
	private int productId;

}
