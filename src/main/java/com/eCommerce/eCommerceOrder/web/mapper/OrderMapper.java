package com.eCommerce.eCommerceOrder.web.mapper;

import java.time.LocalDate;
import java.util.stream.Collectors;

import com.eCommerce.eCommerceOrder.constants.OrderStatus;
import com.eCommerce.eCommerceOrder.entity.ConsumerEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.RequestItem;

public class OrderMapper {

	public ConsumerEntity mapRequestToConsumerEntity(InputOrder request) {
		
		ConsumerEntity entity = new ConsumerEntity();
		
		entity.setBillingAddress(request.getBillingAddress());
		entity.setEmailId(request.getEmailId());
		entity.setName(request.getName());
		entity.setPhoneNo(request.getPhoneNo());
		entity.setShippingAddress(request.getShippingAddress());
		
		return entity;
		
	}

	public static ConsumerLineItemEntity mapRequestToConsumerLineItemEntity(RequestItem request) {
		ConsumerLineItemEntity entity = new ConsumerLineItemEntity();
		
		entity.setItemQuantity(request.getQuantity());
		entity.setModel(request.getModelNumber());
		entity.setStatus(OrderStatus.CREATED);
		entity.setItemName(request.getItemName());
		return entity ;
		
	}

	public ConsumerOrderEntity mapRequestToConsumerOrder(InputOrder request) {
		ConsumerOrderEntity consumerOrderEntity = new ConsumerOrderEntity();
		consumerOrderEntity.setDeliveryDate(LocalDate.now().plusDays(7));
		consumerOrderEntity.setModeOfPayment(request.getModeOfPayment());
		consumerOrderEntity.setOrderStatus(OrderStatus.CREATED);
		consumerOrderEntity.setSubmitDate(LocalDate.now());
		
		consumerOrderEntity.setTotalItemsInOrder(request.getRequestItemList()
				.stream()
				.filter(x->x.getQuantity()>0)
				.mapToInt(RequestItem::getQuantity).sum());
		return consumerOrderEntity;
	}
	
	
	

}
