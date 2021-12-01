package com.eCommerce.eCommerceOrder.service;

import org.springframework.stereotype.Service;

import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;

@Service
public interface UpdateOrderService {

	 OrderResponse updateOrder(String orderId, InputOrder request) ;
}
