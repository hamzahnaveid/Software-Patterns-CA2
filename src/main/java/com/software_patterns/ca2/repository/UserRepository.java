package com.software_patterns.ca2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.software_patterns.ca2.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
