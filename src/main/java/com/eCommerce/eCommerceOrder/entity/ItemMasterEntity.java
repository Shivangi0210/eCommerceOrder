package com.eCommerce.eCommerceOrder.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class ItemMasterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String itemNumber;
	
	private String modelNumber;
	private Double itemPrice;
	private String itemStatus;
	private String itemDesc;
	private String itemName;
	
	
}
