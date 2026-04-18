package com.software_patterns.ca2.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.software_patterns.ca2.state.OrderState;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Date date;
	private double amount;
	private String address;
	private String payment;
	
	@Transient
	private OrderState state;
	
	private String orderStatus;
	private double total;
	private double discount;
	private UUID trackingId;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_email", referencedColumnName = "email")
	private User user;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
	private List<CartItem> cartItems;
	
	public void setState(OrderState state) {
	    this.state = state;
	    this.orderStatus = state.getStatus();
	}

	public void nextState() {
	    this.state.next(this);
	}
}
