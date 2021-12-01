package com.eCommerce.eCommerceOrder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Item doesn't exists in inventory")
public class ItemNotFoundException extends RuntimeException{

	public ItemNotFoundException() {
		super();
	}
}
