package com.eCommerce.eCommerceOrder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.eCommerce.eCommerceOrder.constants.OrderStatus;
import com.eCommerce.eCommerceOrder.dao.ConsumerLineItemRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;



@ExtendWith(MockitoExtension.class)
public class DeleteOrderServiceImplTest {

	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	ConsumerLineItemRepository consumerLineItemRepository;
	
	@InjectMocks
	DeleteOrderServiceImpl serviceImpl;
	
	@Test
	public void deleteOrder_success_test() {
		
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
						.totalItemsInOrder(4).consumerlineItemId(lineItemList)
						.build()));
		
		String result = serviceImpl.deleteOrder("1");
		
		assertEquals("Order Succesfully Cancelled",result);
		
	}

}
