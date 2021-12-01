package com.eCommerce.eCommerceOrder.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.eCommerce.eCommerceOrder.constants.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Entity
public class ConsumerOrderEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderId")
	private String orderId;
	
	@OneToMany
	private List<ConsumerLineItemEntity> consumerlineItemId;
	private String consumerId;
	private OrderStatus orderStatus;	
	private Double totalAmount;
	private LocalDate submitDate;
	private LocalDate deliveryDate;
	private String modeOfPayment;
	private int totalItemsInOrder;
	
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<ConsumerLineItemEntity> getConsumerlineItemId() {
		return consumerlineItemId;
	}
	public void setConsumerlineItemId(List<ConsumerLineItemEntity> consumerlineItemId) {
		this.consumerlineItemId = consumerlineItemId;
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus created) {
		this.orderStatus = created;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public LocalDate getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(LocalDate submitDate) {
		this.submitDate = submitDate;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public int getTotalItemsInOrder() {
		return totalItemsInOrder;
	}
	public void setTotalItemsInOrder(int totalItemsInOrder) {
		this.totalItemsInOrder = totalItemsInOrder;
	}
	
	
}
