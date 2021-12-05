package com.eCommerce.eCommerceOrder.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.eCommerceOrder.dao.ConsumerLineItemRepository;
import com.eCommerce.eCommerceOrder.dao.ConsumerRepository;
import com.eCommerce.eCommerceOrder.dao.ItemMasterRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ConsumerEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.entity.ItemMasterEntity;
import com.eCommerce.eCommerceOrder.exception.ItemNotFoundException;
import com.eCommerce.eCommerceOrder.web.mapper.OrderMapper;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;
import com.eCommerce.eCommerceOrder.web.model.RequestItem;

import lombok.extern.slf4j.Slf4j;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	ConsumerRepository consumerRepo;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ConsumerLineItemRepository consumerLineItemRepository;
	
	
	@Autowired
	ItemMasterRepository itemMasterRepository;
	
	public OrderResponse createOrder(InputOrder request) {
		
		Double totalAmount =  0.0;
		OrderMapper mapper = new OrderMapper();		
		
		
		totalAmount = request.getRequestItemList().stream().mapToDouble(x->{
			Double value = itemMasterRepository.findPriceByItemName(x.getItemName());
			x.setSellingPrice(value);
			return value!=null? value*x.getQuantity() : 0.0;
		}).sum();
		
		Optional<ConsumerEntity> consumerEntity = consumerRepo.findByName(request.getName());
		if(!consumerEntity.isPresent()){
			consumerEntity = Optional.ofNullable(mapper.mapRequestToConsumerEntity(request));
			consumerRepo.save(consumerEntity.get());
			
		}
		
		List<ConsumerLineItemEntity> consumerLineItemEntity = new ArrayList<>();
		List<RequestItem> updatedList = new ArrayList<>();
		for(RequestItem x : request.getRequestItemList()) {
			Optional<ItemMasterEntity> itemMasterEntity = itemMasterRepository.findByItemName(x.getItemName());
			if(itemMasterEntity.isPresent()) {
				ConsumerLineItemEntity entity = mapper.mapRequestToConsumerLineItemEntity(x);
				entity.setSellingPrice(itemMasterEntity.get().getItemPrice());
				consumerLineItemEntity.add(entity);
				updatedList.add(x);
			}
		}
		
		
		
		ConsumerOrderEntity consumerOrderEntity = mapper.mapRequestToConsumerOrder(request.getModeOfPayment(),consumerLineItemEntity);
		consumerOrderEntity.setConsumerlineItem(consumerLineItemEntity);
		consumerEntity.ifPresent(x->consumerOrderEntity.setConsumerId(x.getConsumerId()));
		consumerOrderEntity.setTotalAmount(totalAmount);
		
		orderRepository.save(consumerOrderEntity);
		
		
		return mapper.orderResponse(consumerEntity.get(), consumerOrderEntity, updatedList);
	}

	
	
	
	

}
