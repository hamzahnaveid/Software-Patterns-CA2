package com.software_patterns.ca2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software_patterns.ca2.entity.User;
import com.software_patterns.ca2.repository.UserRepository;

@Service
public class UserDao {
	
	@Autowired
	private UserRepository repo;
	
	public void save(User user) {
		repo.save(user);
	}
	
	public Optional<User> findByEmail(String email) {
		return repo.findById(email);
	}
	
	public void delete(User user) {
		repo.delete(user);
	}
	
	public void deleteByEmail(String email) {
		repo.deleteById(email);
	}
	
	public boolean userExists(String email) {
		return repo.existsById(email);
	}

}
