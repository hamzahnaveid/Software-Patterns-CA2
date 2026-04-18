package com.software_patterns.ca2.factory;

import org.springframework.stereotype.Component;

import com.software_patterns.ca2.builder.OrderBuilder;
import com.software_patterns.ca2.entity.Order;
import com.software_patterns.ca2.entity.User;

@Component
public class OrderFactory {

    public Order createOrder(User user) {
        return new OrderBuilder()
        		.setUser(user)
        		.build();
    }
}