package com.eCommerce.eCommerceOrder.web.model;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
@Setter
public class InputOrder {
	
	@NotNull
	private String name;
	
	@Email
	private String emailId;
	
	private String phoneNo;
	
	@NotNull
	private String shippingAddress;
	
	@NotNull
	private String billingAddress;
	
	private String modeOfPayment;
	
	private List<RequestItem> requestItemList;
	
	


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

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public List<RequestItem> getRequestItemList() {
		return requestItemList;
	}

	public void setRequestItemList(List<RequestItem> requestItemList) {
		this.requestItemList = requestItemList;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	
	
}
