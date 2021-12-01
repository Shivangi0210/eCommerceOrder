package com.eCommerce.eCommerceOrder.entity;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String consumerId;
	
	
	@OneToMany
	private List<ConsumerOrderEntity> orderId;
	
	private String name;
	
	private String phoneNo;
	
	private String emailId;
	
	private String shippingAddress;
	
	private String billingAddress;

	
	
	
	
}
