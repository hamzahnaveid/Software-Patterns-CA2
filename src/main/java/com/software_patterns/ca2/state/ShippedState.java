package com.software_patterns.ca2.state;

import com.software_patterns.ca2.entity.Order;

public class ShippedState implements OrderState {

	@Override
	public void next(Order order) {
		order.setState(new DeliveredState());
	}

	@Override
	public String getStatus() {
		return "SHIPPED";
	}

}
