package com.software_patterns.ca2.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.software_patterns.ca2.entity.CartItem;
import com.software_patterns.ca2.entity.Order;
import com.software_patterns.ca2.entity.User;
import com.software_patterns.ca2.state.PendingState;

public class OrderBuilder {
	
    private User user;
    private double discount;

    public OrderBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public OrderBuilder setDiscount(double discount) {
		this.discount = discount;
		return this;
	}

    public Order build() {
        Order order = new Order();
        order.setAddress(user.getShippingAddress());
        order.setPayment(user.getPaymentMethod());
        order.setUser(user);
        order.setAmount(0);
        order.setDiscount(0);
        order.setCartItems(new ArrayList<CartItem>());
        order.setDate(new Date());
        order.setTrackingId(UUID.randomUUID());
        order.setState(new PendingState());
        order.setTotal(0);

        return order;
    }
}
