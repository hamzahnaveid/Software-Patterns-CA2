package com.software_patterns.ca2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.software_patterns.ca2.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{

}
