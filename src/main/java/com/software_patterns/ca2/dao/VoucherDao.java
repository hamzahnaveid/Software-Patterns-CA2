package com.software_patterns.ca2.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.software_patterns.ca2.entity.Product;
import com.software_patterns.ca2.entity.Voucher;
import com.software_patterns.ca2.repository.VoucherRepository;

@Service
public class VoucherDao {
	
	@Autowired
	private VoucherRepository repo;
	
	public void save(Voucher voucher) {
		repo.save(voucher);
	}
	
	public Optional<Voucher> findById(int id) {
		return repo.findById(id);
	}
	
	public void delete(Voucher voucher) {
		repo.delete(voucher);
	}
	
	public void deleteById(int id) {
		repo.deleteById(id);
	}
	
	public List<Voucher> findAll() {
		return (List<Voucher>) repo.findAll();
	}

}
