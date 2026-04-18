package com.software_patterns.ca2.dto;

import lombok.Data;

@Data
public class CartItemDto {
	
	private int id;
	private double price;
	private double quantity;
	private int productId;
	private int orderId;
	private String userEmail;
	private String productName;
	private byte[] image;

}
