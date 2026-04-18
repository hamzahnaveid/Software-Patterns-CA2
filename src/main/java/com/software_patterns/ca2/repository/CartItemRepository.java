package com.software_patterns.ca2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.software_patterns.ca2.entity.CartItem;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Integer>{
	
	CartItem findByProductIdAndOrderIdAndUserEmail(int productId, int orderId, String email);

}
