package com.eCommerce.eCommerceOrder.controller;

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
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.*;

import com.eCommerce.eCommerceOrder.service.DeleteOrderService;
import com.eCommerce.eCommerceOrder.service.OrderService;
import com.eCommerce.eCommerceOrder.service.UpdateOrderService;
import com.eCommerce.eCommerceOrder.service.ViewOrderService;
import com.eCommerce.eCommerceOrder.web.controller.OrderController;
import com.eCommerce.eCommerceOrder.web.model.InputOrder;
import com.eCommerce.eCommerceOrder.web.model.OrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
	
	@Mock
	OrderService orderService;

	@Mock
	ViewOrderService viewOrderService;

	@Mock
	UpdateOrderService updateOrderService;

	@Mock
	DeleteOrderService deleteOrderService;
	
	
	@InjectMocks
	OrderController controller;
	
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
	void createOrder_test() {
		
		//given
		given(orderService.createOrder(requestJson)).willReturn(new OrderResponse());
		
		ResponseEntity<OrderResponse> output = controller.createOrder(requestJson);
		
		then(orderService).should().createOrder(requestJson);
		
		assertEquals("201 CREATED",output.getStatusCode().toString());
		
	}
	
	@Test
	void viewOrder_test() {
		
		//given
		given(viewOrderService.viewOrder(anyString())).willReturn(new OrderResponse());
		
		ResponseEntity<OrderResponse> output = controller.viewOrder("1");
		
		then(viewOrderService).should().viewOrder("1");
		
		assertEquals("200 OK",output.getStatusCode().toString());
		
	}
	
	@Test
	void updateOrder_test() {
		
		//given
		given(updateOrderService.updateOrder("1",requestJson)).willReturn(new OrderResponse());
		
		ResponseEntity<OrderResponse> output = controller.updateOrder("1",requestJson);
		
		then(updateOrderService).should().updateOrder("1",requestJson);
		
		assertEquals("200 OK",output.getStatusCode().toString());
		
	}
	
	@Test
	void cancelOrder_test() {
		
		//given
		given(deleteOrderService.deleteOrder(anyString())).willReturn(anyString());
		
		ResponseEntity<String> output = controller.deleteOrder("1");
		
		then(deleteOrderService).should().deleteOrder("1");
		
		assertEquals("200 OK",output.getStatusCode().toString());
		
	}
	
	
	
}
