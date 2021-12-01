package com.eCommerce.eCommerceOrder.service;

import org.springframework.stereotype.Service;

@Service
public interface DeleteOrderService {
	
	String deleteOrder(String orderId);

}
