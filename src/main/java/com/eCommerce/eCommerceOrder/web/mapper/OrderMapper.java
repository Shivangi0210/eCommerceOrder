package com.eCommerce.eCommerceOrder.web.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.eCommerce.eCommerceOrder.constants.OrderStatus;
import com.eCommerce.eCommerceOrder.entity.ConsumerEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;
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

	public ConsumerLineItemEntity mapRequestToConsumerLineItemEntity(RequestItem request) {
		ConsumerLineItemEntity entity = new ConsumerLineItemEntity();
		
		entity.setItemQuantity(request.getQuantity());
		entity.setModel(request.getModelNumber());
		entity.setStatus(OrderStatus.CREATED);
		entity.setItemName(request.getItemName());
		return entity ;
		
	}

	public ConsumerOrderEntity mapRequestToConsumerOrder(String modeOfPayment, List<ConsumerLineItemEntity> lineItemEntity) {
		ConsumerOrderEntity consumerOrderEntity = new ConsumerOrderEntity();
		consumerOrderEntity.setDeliveryDate(LocalDate.now().plusDays(7));
		consumerOrderEntity.setModeOfPayment(modeOfPayment);
		consumerOrderEntity.setOrderStatus(OrderStatus.CREATED);
		consumerOrderEntity.setSubmitDate(LocalDate.now());
		consumerOrderEntity.setTotalItemsInOrder(lineItemEntity
				.stream()
				.filter(x->x.getItemQuantity()>0)
				.mapToInt(ConsumerLineItemEntity::getItemQuantity).sum());
		return consumerOrderEntity;
	}
	
	public OrderResponse orderResponse(ConsumerEntity consumerEntity,
			ConsumerOrderEntity consumerOrderEntity, List<RequestItem> input) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setEmailId(consumerEntity.getEmailId());
		orderResponse.setName(consumerEntity.getName());
		orderResponse.setOrderId(consumerOrderEntity.getOrderId());
		orderResponse.setPhoneNo(consumerEntity.getPhoneNo());
		orderResponse.setRequestItemsList(input);
		orderResponse.setTotalAmountofOrder(consumerOrderEntity.getTotalAmount());
		orderResponse.setTotalItemsOrdered(consumerOrderEntity.getTotalItemsInOrder());
		orderResponse.setDeliveryDate(consumerOrderEntity.getDeliveryDate());
		orderResponse.setOrderCreatedDate(consumerOrderEntity.getSubmitDate());
		
		return orderResponse;
	}
	
	

}
