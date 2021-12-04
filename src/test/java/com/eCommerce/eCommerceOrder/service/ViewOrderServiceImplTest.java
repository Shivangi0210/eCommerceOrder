package com.eCommerce.eCommerceOrder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.eCommerce.eCommerceOrder.constants.OrderStatus;
import com.eCommerce.eCommerceOrder.dao.ConsumerRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ConsumerEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.exception.OrderNotFoundException;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;
import com.eCommerce.eCommerceOrder.web.model.RequestItem;

import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ViewOrderServiceImplTest {
	
	@Mock
	ConsumerRepository consumerRepo;
	
	@Mock
	OrderRepository orderRepository;
	
	@InjectMocks
	ViewOrderServiceImpl serviceImpl;
	
	
	@Test
	void viewOrder_success_test() {
		
		
		List<ConsumerLineItemEntity> lineItemList = new ArrayList<>();
		ConsumerLineItemEntity lineItem = ConsumerLineItemEntity.builder()
				.consumerLineItemId("1")
				.itemName("abc")
				.itemNumber("123")
				.itemQuantity(4)
				.model("abc123")
				.sellingPrice(123.11)
				.status(OrderStatus.SHIPPED)
				.build();
		lineItemList.add(lineItem);
		
		given(orderRepository.findById(anyString())).willReturn(Optional
				.ofNullable(ConsumerOrderEntity.builder()
						.consumerId("1")
						.deliveryDate(LocalDate.now().plusDays(7))
						.modeOfPayment("cash")
						.orderId("1")
						.orderStatus(OrderStatus.CREATED)
						.submitDate(LocalDate.now())
						.totalAmount(246.11)
						.totalItemsInOrder(4).consumerlineItem(lineItemList)
						.build()));
		  
		  given(consumerRepo.findById(anyString())).willReturn(Optional.ofNullable(
				  ConsumerEntity.builder()
				  .billingAddress("test 123")
				  .consumerId("1")
				  .billingAddress("test 123")
				  .emailId("test1@gmail.com")
				  .name("test1")
				  .phoneNo("123456")
				  .build()));
		  
		  OrderResponse response = serviceImpl.viewOrder("1");
		  
		  assertNotNull(response);
		 
	}
	
	@Test
	void viewOrder_orderNotPresent_test() {
		
		OrderNotFoundException exception = assertThrows(OrderNotFoundException.class,
				()->{
					given(orderRepository.findById(anyString())).willThrow(OrderNotFoundException.class);
					  
					  OrderResponse response = serviceImpl.viewOrder("10");
				});
		
		
		  
		assertNotNull(exception);
		 
	}

}
