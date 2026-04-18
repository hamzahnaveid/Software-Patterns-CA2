package com.software_patterns.ca2.state;

import com.software_patterns.ca2.entity.Order;

public class PlacedState implements OrderState {

	@Override
	public void next(Order order) {
		order.setState(new ShippedState());
	}

	@Override
	public String getStatus() {
		return "PLACED";
	}

}
