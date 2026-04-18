package com.software_patterns.ca2.state;

import com.software_patterns.ca2.entity.Order;

public class DeliveredState implements OrderState {

	@Override
	public void next(Order order) {
		
	}

	@Override
	public String getStatus() {
		return "DELIVERED";
	}

}
