package com.software_patterns.ca2.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.software_patterns.ca2.dao.OrderDao;
import com.software_patterns.ca2.dao.UserDao;
import com.software_patterns.ca2.dto.LoginRequest;
import com.software_patterns.ca2.dto.RegisterRequest;
import com.software_patterns.ca2.entity.User;
import com.software_patterns.ca2.enums.UserRole;
import com.software_patterns.ca2.factory.OrderFactory;
import com.software_patterns.ca2.utils.JwtUtil;

@RestController
public class AuthController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderFactory orderFactory;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request, @RequestParam String role) {
		if (userDao.userExists(request.getEmail())) {
			return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
		}
		
		User user = new User();
		
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setName(request.getName());
		
		if (role.equals("admin")) {
			user.setRole(UserRole.ADMIN);
		}
		else {
			user.setRole(UserRole.CUSTOMER);
		}
		
		userDao.save(user);
		
		if(user.getRole() == UserRole.CUSTOMER) {
			orderDao.save(orderFactory.createOrder(user));

		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		if (!userDao.userExists(request.getEmail())) {
			return new ResponseEntity<>("Invalid credentials", HttpStatus.NOT_ACCEPTABLE);
		}
		
		User user = userDao.findByEmail(request.getEmail()).get();
		
		if (user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword())) {
			String token = jwtUtil.generateToken(user);

	        Map<String, Object> response = new HashMap<>();
	        response.put("token", token);
	        response.put("user", user);

			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Invalid credentials", HttpStatus.NOT_ACCEPTABLE);
	}

}
