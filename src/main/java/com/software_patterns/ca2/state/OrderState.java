package com.software_patterns.ca2.state;

import com.software_patterns.ca2.entity.Order;

public interface OrderState {
	void next(Order order);
	String getStatus();

}
