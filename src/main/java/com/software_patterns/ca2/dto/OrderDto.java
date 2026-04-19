package com.software_patterns.ca2.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.software_patterns.ca2.state.OrderState;

import lombok.Data;

@Data
public class OrderDto {
	private int id;
	private Date date;
	private double amount;
	private String address;
	private String payment;
	private OrderState state;
	private String orderStatus;
	private double total;
	private double discount;
	private UUID trackingId;
	private String userName;
	private List<CartItemDto> cartItems;
	private String voucherName;

}
