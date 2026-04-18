package com.software_patterns.ca2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.software_patterns.ca2.dao.VoucherDao;
import com.software_patterns.ca2.dto.VoucherDto;
import com.software_patterns.ca2.entity.Voucher;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminVoucherController {
	
	@Autowired
	VoucherDao voucherDao;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add-voucher")
	@ResponseBody
	public ResponseEntity<Voucher> addVoucher(@RequestBody VoucherDto request) {
		Voucher voucher = new Voucher();
		
		voucher.setName(request.getName());
		voucher.setCode(request.getCode());
		voucher.setDiscount(request.getDiscount());
		
		voucherDao.save(voucher);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(voucher);

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-vouchers")
	@ResponseBody
	public ResponseEntity<List<Voucher>> getAllVouchers() {
		return new ResponseEntity<List<Voucher>>(voucherDao.findAll(), HttpStatus.OK);
	}

}
