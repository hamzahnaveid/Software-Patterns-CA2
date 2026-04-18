package com.software_patterns.ca2.state;

import com.software_patterns.ca2.entity.Order;

public class PendingState implements OrderState {

	@Override
	public void next(Order order) {
		order.setState(new PlacedState());
	}

	@Override
	public String getStatus() {
		return "PENDING";
	}

}
