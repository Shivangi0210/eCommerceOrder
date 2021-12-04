package com.eCommerce.eCommerceOrder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerceOrder.dao.ConsumerLineItemRepository;
import com.eCommerce.eCommerceOrder.dao.ConsumerRepository;
import com.eCommerce.eCommerceOrder.dao.ItemMasterRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ConsumerEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.exception.OrderNotFoundException;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;
import com.eCommerce.eCommerceOrder.web.model.RequestItem;
import com.netflix.discovery.provider.Serializer;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ViewOrderServiceImpl implements ViewOrderService{
	
	@Autowired
	ConsumerRepository consumerRepo;
	
	@Autowired
	OrderRepository orderRepository;

	
	
	@Override
	public OrderResponse viewOrder(String orderId) {
		
		Optional<ConsumerOrderEntity> order = Optional.ofNullable(orderRepository.findById(orderId)
				.orElseThrow(OrderNotFoundException::new));
		
		Optional<ConsumerEntity> consumer = Optional.ofNullable(new ConsumerEntity());
		
		consumer = consumerRepo.findById(order.get().getConsumerId());
		
		
		OrderResponse response = new OrderResponse();
		if(consumer.isPresent()) {
			response.setEmailId(consumer.get().getEmailId());
			response.setName(consumer.get().getName());
			response.setOrderId(orderId);
			response.setPhoneNo(consumer.get().getPhoneNo());
			response.setTotalAmountofOrder(order.get().getTotalAmount());
			response.setTotalItemsOrdered(order.get().getTotalItemsInOrder());
			List<RequestItem> itemList = new ArrayList<>();
			order.get().getConsumerlineItem().stream()
				.forEach(x->{
					RequestItem item = new RequestItem();
					item.setItemName(x.getItemName());
					item.setModelNumber(x.getModel());
					item.setQuantity(x.getItemQuantity());
					item.setSellingPrice(x.getSellingPrice());
					itemList.add(item);
				});
			response.setRequestItemsList(itemList);
		}
		return response;
		
	}

}
