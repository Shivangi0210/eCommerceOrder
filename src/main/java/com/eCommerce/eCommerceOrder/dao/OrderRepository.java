package com.eCommerce.eCommerceOrder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.eCommerceOrder.entity.ConsumerOrderEntity;


@Repository
public interface OrderRepository extends JpaRepository<ConsumerOrderEntity, String>{

}
