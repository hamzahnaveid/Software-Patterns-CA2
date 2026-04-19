package com.software_patterns.ca2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.software_patterns.ca2.dao.CartItemDao;
import com.software_patterns.ca2.dao.OrderDao;
import com.software_patterns.ca2.dao.ProductDao;
import com.software_patterns.ca2.dao.UserDao;
import com.software_patterns.ca2.dao.VoucherDao;
import com.software_patterns.ca2.dto.CartItemDto;
import com.software_patterns.ca2.dto.OrderDto;
import com.software_patterns.ca2.dto.ProductToCartRequest;
import com.software_patterns.ca2.entity.CartItem;
import com.software_patterns.ca2.entity.Order;
import com.software_patterns.ca2.entity.Product;
import com.software_patterns.ca2.entity.User;
import com.software_patterns.ca2.entity.Voucher;
import com.software_patterns.ca2.service.ProductSearchService;
import com.software_patterns.ca2.service.ProductSortService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	CartItemDao cartItemDao;
	
	@Autowired
	VoucherDao voucherDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductSearchService searchService;
	
	@Autowired
	ProductSortService sortService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get-products")
	@ResponseBody
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<List<Product>>(productDao.findAll(), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/search")
	@ResponseBody
	public ResponseEntity<List<Product>> getProductsByTermAndSort(@RequestParam String term, @RequestParam String sortBy, @RequestParam String type) {
		List<Product> products;
		
		if (term.isEmpty()) {
			products = productDao.findAll();
		}
		else {
			products = searchService.search(term);
		}
		
		products = sortService.sort(products, sortBy, type);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add-to-cart")
	@ResponseBody
	public ResponseEntity<?> addProductToCart(@RequestBody ProductToCartRequest request) {
		Order activeOrder = orderDao.findByUserEmailAndStatus(request.getUserEmail(), "PENDING");
		
		Product product = productDao.findById(request.getProductId()).get();
		User user = userDao.findByEmail(request.getUserEmail()).get();
		
		CartItem cart = new CartItem();
		cart.setProduct(product);
		cart.setPrice(product.getPrice());
		cart.setQuantity(request.getQuantity());
		cart.setUser(user);
		cart.setOrder(activeOrder);
		
		activeOrder.addItem(cart);
		
		cartItemDao.save(cart);
		
		double newTotal = activeOrder.getTotal();
		activeOrder.setTotal(newTotal);
		activeOrder.setAmount(newTotal);

		orderDao.save(activeOrder);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cart);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/cart/{userEmail}")
	public ResponseEntity<?> getCartByUserEmail(@PathVariable String userEmail) {
		Order activeOrder = orderDao.findByUserEmailAndStatus(userEmail, "PENDING");
		List<CartItemDto> cartItemDtoList = activeOrder.getCartItems().stream().map(CartItem::getCartDto).collect(Collectors.toList());
		
		OrderDto orderDto = new OrderDto();
		
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setId(activeOrder.getId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setDiscount(activeOrder.getDiscount());
		orderDto.setTotal(activeOrder.getTotal());
		orderDto.setCartItems(cartItemDtoList);;
		
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/apply-voucher/{userEmail}/{code}")
	@ResponseBody
	public ResponseEntity<?> applyVoucher(@PathVariable String userEmail, @PathVariable String code) {
		Order activeOrder = orderDao.findByUserEmailAndStatus(userEmail, "PENDING");
		Voucher voucher = voucherDao.findByCode(code);
		
		if (voucher == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid voucher");
		}
		
		double discountAmount = (voucher.getDiscount() / 100.0) * activeOrder.getTotal();
		double finalAmount = activeOrder.getTotal() - discountAmount;
		
		activeOrder.setDiscount(discountAmount);
		activeOrder.setAmount(finalAmount);
		
		activeOrder.setVoucher(voucherDao.findByCode(code));	
		orderDao.save(activeOrder);
		
		return new ResponseEntity<>(activeOrder.getOrderDto(), HttpStatus.OK);
	}

}
