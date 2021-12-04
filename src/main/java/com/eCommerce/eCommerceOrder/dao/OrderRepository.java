package com.eCommerce.eCommerceOrder.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;
import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;


@Repository
public interface OrderRepository extends JpaRepository<ConsumerOrderEntity, String>{

	@Query("SELECT co.consumerlineItem  FROM ConsumerOrderEntity co WHERE co.orderId=:a")
	List<ConsumerLineItemEntity> findLineItemByOrderId(@Param("a") String orderId);
}
