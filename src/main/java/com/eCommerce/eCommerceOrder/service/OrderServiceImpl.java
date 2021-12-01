package com.eCommerce.eCommerceOrder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		Double value1 = itemMasterRepository.findPriceByItemName(request.getRequestItemList().get(0).getItemName());
		
		
		totalAmount = request.getRequestItemList().stream().mapToDouble(x->{
			Double value = itemMasterRepository.findPriceByItemName(x.getItemName());
			return value*x.getQuantity();
		}).sum();
		
		
		ConsumerEntity consumerEntity = mapper.mapRequestToConsumerEntity(request);
		
		consumerRepo.save(consumerEntity);
		
		List<ConsumerLineItemEntity> consumerLineItemEntity = request.getRequestItemList().stream()
				.map(OrderMapper :: mapRequestToConsumerLineItemEntity)
				.collect(Collectors.toList());
		
		consumerLineItemEntity.stream().forEach(x->x.setItemNumber(itemMasterRepository.findByItemName(x.getItemName()).getItemNumber()));
		
		consumerLineItemRepository.saveAll(consumerLineItemEntity);
		
		ConsumerOrderEntity consumerOrderEntity = mapper.mapRequestToConsumerOrder(request);
		consumerOrderEntity.setConsumerlineItemId(consumerLineItemEntity);
		consumerOrderEntity.setConsumerId(consumerEntity.getConsumerId());
		consumerOrderEntity.setTotalAmount(totalAmount);
		orderRepository.save(consumerOrderEntity);
		
		return orderResponse(consumerEntity, consumerLineItemEntity, consumerOrderEntity, request.getRequestItemList());
	}

	private OrderResponse orderResponse(ConsumerEntity consumerEntity, List<ConsumerLineItemEntity> consumerLineItemEntity,
			ConsumerOrderEntity consumerOrderEntity, List<RequestItem> input) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setEmailId(consumerEntity.getEmailId());
		orderResponse.setName(consumerEntity.getName());
		orderResponse.setOrderId(consumerOrderEntity.getOrderId());
		orderResponse.setPhoneNo(consumerEntity.getPhoneNo());
		orderResponse.setRequestItemsList(input);
		orderResponse.setTotalAmountofOrder(consumerOrderEntity.getTotalAmount());
		orderResponse.setTotalItemsOrdered(consumerOrderEntity.getTotalItemsInOrder());
		
		return orderResponse;
	}

	

}
