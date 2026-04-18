package com.software_patterns.ca2.dto;

import lombok.Data;

@Data
public class VoucherDto {
	
	private int id;
	private String name;
	private String code;
	private double discount;

}
