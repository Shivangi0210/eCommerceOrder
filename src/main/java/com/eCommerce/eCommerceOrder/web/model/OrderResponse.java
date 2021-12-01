package com.eCommerce.eCommerceOrder.web.model;

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
	private List<RequestItem> requestItemsList;
	private Double totalAmountofOrder;
	private int totalItemsOrdered;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public List<RequestItem> getRequestItemsList() {
		return requestItemsList;
	}
	public void setRequestItemsList(List<RequestItem> requestItemsList) {
		this.requestItemsList = requestItemsList;
	}
	public Double getTotalAmountofOrder() {
		return totalAmountofOrder;
	}
	public void setTotalAmountofOrder(Double totalAmountofOrder) {
		this.totalAmountofOrder = totalAmountofOrder;
	}
	public int getTotalItemsOrdered() {
		return totalItemsOrdered;
	}
	public void setTotalItemsOrdered(int totalItemsOrdered) {
		this.totalItemsOrdered = totalItemsOrdered;
	}

	
}
