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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	
	
}
