package com.software_patterns.ca2.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductRequest {
	
	private int id;
	private String name;
	private String manufacturer;
	private double price;
	private int stock;
	private int categoryId;
	private String description;
	private byte[] byteImage;
	private MultipartFile image;

}
