package com.eCommerce.eCommerceOrder.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.eCommerce.eCommerceOrder.constants.OrderStatus;

@Entity
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

	public String getConsumerLineItemId() {
		return consumerLineItemId;
	}

	public void setConsumerLineItemId(String consumerLineItemId) {
		this.consumerLineItemId = consumerLineItemId;
	}


	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus created) {
		this.status = status;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	

}
