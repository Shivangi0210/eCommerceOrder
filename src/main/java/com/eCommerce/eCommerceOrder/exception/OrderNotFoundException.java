package com.eCommerce.eCommerceOrder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Order not present in database")
public class OrderNotFoundException extends RuntimeException {

	

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException() {
		super();
	}

}
