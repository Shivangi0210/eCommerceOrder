package com.eCommerce.eCommerceOrder.service;

import org.springframework.stereotype.Service;

import com.eCommerce.eCommerceOrder.web.model.OrderResponse;

@Service
public interface ViewOrderService {

	public OrderResponse  viewOrder(String orderId) ;
	
}
