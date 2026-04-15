package com.software_patterns.ca2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.software_patterns.ca2.dao.UserDao;
import com.software_patterns.ca2.dto.RegisterRequest;
import com.software_patterns.ca2.entity.User;
import com.software_patterns.ca2.enums.UserRole;

@RestController
public class AuthController {
	
	@Autowired
	UserDao userDao;
	
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
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
