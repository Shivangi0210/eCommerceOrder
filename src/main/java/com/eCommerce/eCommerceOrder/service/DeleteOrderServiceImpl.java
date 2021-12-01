package com.eCommerce.eCommerceOrder.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerceOrder.dao.ConsumerLineItemRepository;
import com.eCommerce.eCommerceOrder.dao.ConsumerRepository;
import com.eCommerce.eCommerceOrder.dao.ItemMasterRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;

@Service
public class DeleteOrderServiceImpl implements DeleteOrderService {
	
	
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ConsumerLineItemRepository consumerLineItemRepository;

	
	
	@Override
	public String deleteOrder(String orderId) {
		Optional<ConsumerOrderEntity> order = orderRepository.findById(orderId);
		
		order.ifPresent(x->{
			x.getConsumerlineItemId().stream().forEach(y->{
				consumerLineItemRepository.deleteById(y.getConsumerLineItemId());
			});
		});
		
		orderRepository.deleteById(orderId);
		
		return "Order Succesfully Cancelled";
	
	}


}
