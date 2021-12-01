package com.eCommerce.eCommerceOrder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.eCommerceOrder.entity.ConsumerLineItemEntity;


@Repository
public interface ConsumerLineItemRepository extends JpaRepository<ConsumerLineItemEntity, String>{

}
