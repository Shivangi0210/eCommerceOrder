package com.eCommerce.eCommerceOrder.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class FailOverRecordsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String recordId;
	private String orderId;
	private String orderStatus;
	private String errorMessage;
	private String processedFlag;
	private Date createDate;
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getProcessedFlag() {
		return processedFlag;
	}
	public void setProcessedFlag(String processedFlag) {
		this.processedFlag = processedFlag;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
}
