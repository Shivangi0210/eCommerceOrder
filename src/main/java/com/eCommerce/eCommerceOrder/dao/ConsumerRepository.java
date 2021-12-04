package com.eCommerce.eCommerceOrder.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eCommerce.eCommerceOrder.entity.ConsumerEntity;

@Repository
public interface ConsumerRepository extends JpaRepository<ConsumerEntity, String>{
	
	
	Optional<ConsumerEntity> findByName(@Param("a")String name);

}
