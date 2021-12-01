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
	
	


	
}
