package com.eCommerce.eCommerceOrder.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eCommerce.eCommerceOrder.entity.ItemMasterEntity;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMasterEntity, String>{
	
	public Optional<ItemMasterEntity> findByItemName(String name);
	
	@Query("SELECT ime.itemPrice FROM ItemMasterEntity ime WHERE ime.itemName=:a")
	public Double findPriceByItemName(@Param("a") String name);

}
