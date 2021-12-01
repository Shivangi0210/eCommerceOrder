package com.eCommerce.eCommerceOrder.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FailOverRecordsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String recordId;
	private String orderId;
	private String orderStatus;
	private String errorMessage;
	private String processedFlag;
	private Date createDate;
	
}
