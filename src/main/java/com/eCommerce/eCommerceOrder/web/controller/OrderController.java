package com.eCommerce.eCommerceOrder.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.eCommerceOrder.service.OrderService;
import com.eCommerce.eCommerceOrder.validation.InputValidation;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;

import lombok.extern.slf4j.Slf4j;


@RestController
public class OrderController {
	
	
	@Autowired
	OrderService orderService;

	
	@PostMapping("/createOrder")
	public ResponseEntity<OrderResponse> createOrder(@Validated @RequestBody InputOrder request){
		
		
			
			OrderResponse orderResponse = orderService.createOrder(request);
		
		
		return new ResponseEntity<>(orderResponse,HttpStatus.CREATED);
		
		
		
	}
	
	
	@PutMapping("/updateOrder/{orderId}")
	public ResponseEntity<OrderResponse> updateOrder(@PathVariable String orderId, @RequestBody InputOrder request){
		return null;
		
	}
	
	@GetMapping("/viewOrder/{orderId}")
	public ResponseEntity<OrderResponse> viewOrder(@PathVariable String orderId){
		return null;
		
	}
	
	
	@DeleteMapping("/cancelOrder/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable String orderId){
		return null;
		
	}

}
