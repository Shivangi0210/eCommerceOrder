package com.eCommerce.eCommerceOrder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerceOrder.constants.OrderStatus;
import com.eCommerce.eCommerceOrder.dao.ConsumerLineItemRepository;
import com.eCommerce.eCommerceOrder.dao.ConsumerRepository;
import com.eCommerce.eCommerceOrder.dao.ItemMasterRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ConsumerEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.exception.ItemNotFoundException;
import com.eCommerce.eCommerceOrder.exception.OrderNotFoundException;
import com.eCommerce.eCommerceOrder.web.mapper.OrderMapper;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;
import com.eCommerce.eCommerceOrder.web.model.RequestItem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UpdateOrderServiceImpl implements UpdateOrderService{

	@Autowired
	ConsumerRepository consumerRepo;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ConsumerLineItemRepository consumerLineItemRepository;
	
	@Autowired
	ItemMasterRepository itemMasterRepository;
	
	
	@Override
	public OrderResponse updateOrder(String orderId, InputOrder request) {

		OrderMapper mapper = new OrderMapper();
		List<RequestItem> updatedList = new ArrayList<>();
		
		Optional<ConsumerOrderEntity> order = Optional.ofNullable(orderRepository.findById(orderId)
				.orElseThrow(OrderNotFoundException::new));
		
		Optional<ConsumerEntity> consumer = Optional.ofNullable(new ConsumerEntity());
		
		consumer = consumerRepo.findById(order.get().getConsumerId());
		
		if(consumer.isPresent()) {
			ConsumerEntity consumerObj = consumer.get();
			ConsumerOrderEntity orderObj = order.get();
			if(!consumerObj.getBillingAddress().equalsIgnoreCase(request.getBillingAddress()))
				consumerObj.setBillingAddress(request.getBillingAddress());
			if(!consumerObj.getEmailId().equalsIgnoreCase(request.getEmailId()))
				consumerObj.setEmailId(request.getEmailId());
			if(!consumerObj.getName().equalsIgnoreCase(request.getName()))
				consumerObj.setName(request.getName());
			if(!consumerObj.getPhoneNo().equalsIgnoreCase(request.getPhoneNo()))
				consumerObj.setPhoneNo(request.getPhoneNo());
			if(!consumerObj.getShippingAddress().equalsIgnoreCase(request.getShippingAddress()))
				consumerObj.setShippingAddress(request.getShippingAddress());
			
			// calculating the total amount of the order
			orderObj.setTotalAmount(orderObj.getTotalAmount()+
					request.getRequestItemList().stream().mapToDouble(x->{
					Double value = itemMasterRepository.findPriceByItemName(x.getItemName());
					x.setSellingPrice(value);
					return value!=null? value*x.getQuantity() : 0.0;
				}).sum());
			
		
			List<ConsumerLineItemEntity> newLineItemList = new ArrayList<>();
			for(RequestItem reqItem: request.getRequestItemList()) {
				orderObj.getConsumerlineItem().stream()
				.filter(x->x.getItemName().equalsIgnoreCase(reqItem.getItemName()))
				.map(x-> {x.setItemQuantity(x.getItemQuantity()+reqItem.getQuantity()); 
							return x;
							})
				.collect(Collectors.toList());
			}
			for(RequestItem reqItem: request.getRequestItemList()) {
				ConsumerLineItemEntity newLineItem = new ConsumerLineItemEntity();
				Double value = itemMasterRepository.findPriceByItemName(reqItem.getItemName());
				if(value!=null && !orderObj.getConsumerlineItem().stream().anyMatch(x->x.getItemName().equalsIgnoreCase(reqItem.getItemName()))) {
					newLineItem.setItemName(reqItem.getItemName());
					newLineItem.setItemQuantity(reqItem.getQuantity());
					newLineItem.setModel(reqItem.getModelNumber());
					newLineItem.setSellingPrice(value);
					newLineItem.setStatus(OrderStatus.CREATED);
					newLineItemList.add(newLineItem);
				}
				
				
			}
			orderObj.getConsumerlineItem().addAll(newLineItemList);
			
			// calculating the total no. of items in order
			orderObj.setTotalItemsInOrder(
					orderObj.getConsumerlineItem()
					.stream()
					.filter(x->x.getItemQuantity()>0)
					.mapToInt(ConsumerLineItemEntity::getItemQuantity).sum());
			
			
			
			orderRepository.saveAndFlush(orderObj);
			
			updatedList = createRequestItemList(orderId);
			consumerRepo.save(consumerObj);
					
		}
		
		log.info("order is updated");
		
		
		return mapper.orderResponse(consumer.get(), order.get(), updatedList);
	}


	private List<RequestItem> createRequestItemList(String orderId) {
		List<RequestItem> updatedList = new ArrayList<>();
		List<ConsumerLineItemEntity> entity = orderRepository.findLineItemByOrderId(orderId);
		
		entity.forEach(x->{
			RequestItem requestItem = new RequestItem();
			requestItem.setItemName(x.getItemName());
			requestItem.setModelNumber(x.getModel());
			requestItem.setQuantity(x.getItemQuantity());
			requestItem.setSellingPrice(x.getSellingPrice());
			updatedList.add(requestItem);
		});
		
		return updatedList;
	}
	
	

	

}
