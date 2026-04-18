package com.software_patterns.ca2.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.software_patterns.ca2.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>{
	
	List<Order> findAllByUserEmail(String email);
	
	Order findByUserEmailAndOrderStatus(String email, String status);

}
