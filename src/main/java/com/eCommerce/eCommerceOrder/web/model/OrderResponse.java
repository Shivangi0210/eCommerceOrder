package com.eCommerce.eCommerceOrder.web.model;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
	
	private String orderId;
	private String name;
	private String emailId;
	private String phoneNo;
	private Double totalAmountofOrder;
	private int totalItemsOrdered;
	private LocalDate deliveryDate;
	private LocalDate OrderCreatedDate;
	private List<RequestItem> requestItemsList;
	
	
}
