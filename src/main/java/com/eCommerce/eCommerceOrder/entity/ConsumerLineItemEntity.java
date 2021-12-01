package com.eCommerce.eCommerceOrder.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.eCommerce.eCommerceOrder.constants.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerLineItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String consumerLineItemId;
	
	
	
	private String itemNumber;
	
	private String model;
	
	private int itemQuantity;
	
	private Double sellingPrice;
	
	private String itemName;
	
	private OrderStatus status;

	
}
