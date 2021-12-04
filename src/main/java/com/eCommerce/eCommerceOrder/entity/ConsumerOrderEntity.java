package com.eCommerce.eCommerceOrder.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name="consumer_order_id")
	private List<ConsumerLineItemEntity> consumerlineItem;
	
	
	private String consumerId;
	
	private OrderStatus orderStatus;	
	private Double totalAmount;
	private LocalDate submitDate;
	private LocalDate deliveryDate;
	private String modeOfPayment;
	private int totalItemsInOrder;
	
	
}
