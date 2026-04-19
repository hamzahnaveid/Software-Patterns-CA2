package com.software_patterns.ca2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.software_patterns.ca2.entity.Voucher;

@Repository
public interface VoucherRepository extends CrudRepository<Voucher, Integer>{
	
	Voucher findByCode(String code);

}
