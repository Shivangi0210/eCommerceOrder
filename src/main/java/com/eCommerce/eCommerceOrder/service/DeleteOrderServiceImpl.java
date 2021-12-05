package com.eCommerce.eCommerceOrder.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerceOrder.dao.ConsumerLineItemRepository;
import com.eCommerce.eCommerceOrder.dao.ConsumerRepository;
import com.eCommerce.eCommerceOrder.dao.ItemMasterRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.exception.OrderNotFoundException;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;

@Transactional
@Service
public class DeleteOrderServiceImpl implements DeleteOrderService {
	
	
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public String deleteOrder(String orderId) {

		if(orderRepository.findById(orderId).isPresent()) {
		  orderRepository.deleteById(orderId);
		}
		else {
			throw new OrderNotFoundException();
		}
			
		return "Order Succesfully Cancelled";
	
	}


}
