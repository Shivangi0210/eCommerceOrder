package com.eCommerce.eCommerceOrder.validation;

import javax.validation.ConstraintValidator;

import org.springframework.stereotype.Component;

import com.eCommerce.eCommerceOrder.web.model.InputOrder;


@Component
public class InputValidation  {
	
	public boolean isValid(InputOrder request) {
		
		return true;
	}
	

}
