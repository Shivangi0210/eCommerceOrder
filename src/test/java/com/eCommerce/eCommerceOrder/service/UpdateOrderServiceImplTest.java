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
import com.eCommerce.eCommerceOrder.dao.ConsumerLineItemRepository;
import com.eCommerce.eCommerceOrder.dao.ConsumerRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ConsumerEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;
import com.eCommerce.eCommerceOrder.exception.OrderNotFoundException;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;
import com.eCommerce.eCommerceOrder.web.model.RequestItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class UpdateOrderServiceImplTest {

	@Mock
	ConsumerRepository consumerRepo;
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	ConsumerLineItemRepository consumerLineItemRepository;
	
	@InjectMocks
	UpdateOrderServiceImpl serviceImpl;
	
	private InputOrder requestJson ;
	
	@BeforeEach
	void setUp() throws JsonMappingException, JsonProcessingException {
		
		String request = "{\r\n"
				+ "\"name\":\"Updatedtest1\",\r\n"
				+ "\"emailId\":\"updatedtest1@gmail.com\",\r\n"
				+ "\"phoneNo\":\"123455\",\r\n"
				+ "\"shippingAddress\":\"12324\",\r\n"
				+ "\"billingAddress\":\"1234\",\r\n"
				+ "\"modeOfPayment\":\"cash\",\r\n"
				+ "\"requestItemList\":[\r\n"
				+ "    {\"itemName\":\"test data1\",\r\n"
				+ "    \"quantity\":2,\r\n"
				+ "    \"modelNumber\":\"test model1\"\r\n"
				+ "    },\r\n"
				+ "     {\"itemName\":\"test data2\",\r\n"
				+ "    \"quantity\":2,\r\n"
				+ "    \"modelNumber\":\"test model2\"\r\n"
				+ "    }\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
		
		ObjectMapper mapper = new ObjectMapper();
		
		requestJson = mapper.readValue(request, InputOrder.class);
		
		
	}
	
	
	@Test
	public void updateOrder_success_test() {
		
		
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
		  
		  given(consumerRepo.findById(anyString())).willReturn(Optional.ofNullable(
				  ConsumerEntity.builder()
				  .billingAddress("bill 123")
				  .consumerId("1")
				  .shippingAddress("ship 123")
				  .emailId("test1@gmail.com")
				  .name("test1")
				  .phoneNo("123456")
				  .build()));
		  
		  OrderResponse response = serviceImpl.updateOrder("1", requestJson);
		
		  assertEquals("Updatedtest1",response.getName());
		
	}

}
