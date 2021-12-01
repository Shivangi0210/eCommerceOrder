package com.eCommerce.eCommerceOrder.web.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestItem {
	
	@NotNull
	private String itemName;
	
	@NotNull
	@Positive
	private int quantity;
	
	@NotNull
	private String modelNumber;
	
	@JsonIgnore
	private Double sellingPrice;


	
}
