package com.eCommerce.eCommerceOrder.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import com.eCommerce.eCommerceOrder.dao.ConsumerLineItemRepository;
import com.eCommerce.eCommerceOrder.dao.ConsumerRepository;
import com.eCommerce.eCommerceOrder.dao.ItemMasterRepository;
import com.eCommerce.eCommerceOrder.dao.OrderRepository;
import com.eCommerce.eCommerceOrder.entity.ItemMasterEntity;
import com.eCommerce.eCommerceOrder.exception.ItemNotFoundException;
import com.eCommerce.eCommerceOrder.service.OrderService;
import com.eCommerce.eCommerceOrder.web.controller.OrderController;
import com.eCommerce.eCommerceOrder.web.mapper.OrderMapper;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

	
	@Mock
	ConsumerRepository consumerRepo;
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	ConsumerLineItemRepository consumerLineItemRepository;
	
	
	@Mock
	ItemMasterRepository itemMasterRepository;
	
	@InjectMocks
	OrderServiceImpl serviceImpl;
	
	private InputOrder requestJson;
	
	@BeforeEach
	void setUp() throws JsonMappingException, JsonProcessingException {
		
		String request = "{\r\n"
				+ "\"name\":\"test1\",\r\n"
				+ "\"emailId\":\"test1@gmail.com\",\r\n"
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
	void createOrder_Success_test() {
		
		//OrderMapper mapper = new OrderMapper();
		
		given(itemMasterRepository.findPriceByItemName(requestJson.getRequestItemList().get(0).getItemName())).willReturn(123.11);
		
		
		given(itemMasterRepository.findByItemName(any(String.class))).willReturn(Optional.ofNullable(ItemMasterEntity.builder().itemNumber("1").itemName("test data1").build()));
		
		OrderResponse response = serviceImpl.createOrder(requestJson);
		
	
		assertEquals(4,response.getTotalItemsOrdered());
		
	}
	
	@Test
	void createOrder_ItemNotFoundException_test() {
		
		//OrderMapper mapper = new OrderMapper();
		
		/*
		 * given(itemMasterRepository.findPriceByItemName(anyString())).willReturn(null)
		 * ;
		 * 
		 * //given(itemMasterRepository.findByItemName(any(String.class))).willReturn(
		 * ItemMasterEntity.builder().itemNumber("1").itemName("test data1").build());
		 * 
		 * OrderResponse response = serviceImpl.createOrder(requestJson);
		 * 
		 * 
		 * assertThrows(ItemNotFoundException.class, null);
		 */
		
	}
	
	
}
