package com.eCommerce.eCommerceOrder.service;

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
		
		Double totalAmount =  Double.NaN;
		OrderMapper mapper = new OrderMapper();		
		
		
		totalAmount = request.getRequestItemList().stream().mapToDouble(x->{
			Double value = itemMasterRepository.findPriceByItemName(x.getItemName());
			x.setSellingPrice(value);
			return value*x.getQuantity();
		}).sum();
		
		Optional<ConsumerEntity> consumerEntity = consumerRepo.findByName(request.getName());
		if(!consumerEntity.isPresent()){
			consumerEntity = Optional.ofNullable(mapper.mapRequestToConsumerEntity(request));
			consumerRepo.save(consumerEntity.get());
			
		}
		
		
		List<ConsumerLineItemEntity> consumerLineItemEntity = request.getRequestItemList().stream()
				.map(OrderMapper :: mapRequestToConsumerLineItemEntity)
				.collect(Collectors.toList());
		
		consumerLineItemEntity.stream()
		.forEach(x->{
					Optional<ItemMasterEntity> itemMasterEntity = itemMasterRepository.findByItemName(x.getItemName());
					x.setItemNumber(itemMasterEntity
						.orElseThrow(()-> new ItemNotFoundException()).getItemNumber());
					x.setSellingPrice(itemMasterEntity.get().getItemPrice());
					
			});
		
		consumerLineItemRepository.saveAll(consumerLineItemEntity);
		
		
		ConsumerOrderEntity consumerOrderEntity = mapper.mapRequestToConsumerOrder(request);
		consumerOrderEntity.setConsumerlineItem(consumerLineItemEntity);
		consumerEntity.ifPresent(x->consumerOrderEntity.setConsumerId(x.getConsumerId()));
		consumerOrderEntity.setTotalAmount(totalAmount);
		
		orderRepository.save(consumerOrderEntity);
		
		return mapper.orderResponse(consumerEntity.get(), consumerOrderEntity, request.getRequestItemList());
	}

	

	

}
