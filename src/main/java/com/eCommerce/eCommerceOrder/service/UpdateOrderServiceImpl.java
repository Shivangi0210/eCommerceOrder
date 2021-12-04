package com.eCommerce.eCommerceOrder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		
		if(order.get().getOrderStatus().equals(OrderStatus.CREATED)&& consumer.isPresent()) {
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
					if(value==null) {
						throw new ItemNotFoundException();
					}
					return value*x.getQuantity();
				}).sum());
			
			// calculating the total no. of items in order
			orderObj.setTotalItemsInOrder(orderObj.getTotalItemsInOrder()+
					request.getRequestItemList()
					.stream()
					.filter(x->x.getQuantity()>0)
					.mapToInt(RequestItem::getQuantity).sum());
			
			
			List<ConsumerLineItemEntity> newLineItemList = new ArrayList<>();
			for(RequestItem reqItem: request.getRequestItemList()) {
				ConsumerLineItemEntity newLineItem = new ConsumerLineItemEntity();
				Double value = itemMasterRepository.findPriceByItemName(reqItem.getItemName());
					newLineItem.setItemName(reqItem.getItemName());
					newLineItem.setItemNumber(reqItem.getItemName());
					newLineItem.setItemQuantity(reqItem.getQuantity());
					newLineItem.setModel(reqItem.getModelNumber());
					newLineItem.setSellingPrice(value);
				
				newLineItemList.add(newLineItem);
				consumerLineItemRepository.save(newLineItem);
			}
			orderObj.setConsumerlineItem(newLineItemList);
			updatedList = createRequestItemList(request.getRequestItemList(),newLineItemList);
			
			consumerRepo.save(consumerObj);
			orderRepository.save(orderObj);
			
		}
		
		log.info("order is updated");
		
		
		return mapper.orderResponse(consumer.get(), order.get(), updatedList);
	}


	private List<RequestItem> createRequestItemList(List<RequestItem> requestItemList,
			List<ConsumerLineItemEntity> newLineItemList) {
		List<RequestItem> updatedList = new ArrayList<>();
		
		requestItemList.forEach(x->{
			RequestItem requestItem = new RequestItem();
			requestItem.setItemName(x.getItemName());
			requestItem.setModelNumber(x.getModelNumber());
			requestItem.setQuantity(x.getQuantity());
			requestItem.setSellingPrice(x.getSellingPrice());
			updatedList.add(requestItem);
		});
		
		newLineItemList.forEach(x->{
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
