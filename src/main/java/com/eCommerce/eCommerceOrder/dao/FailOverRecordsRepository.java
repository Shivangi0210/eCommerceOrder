package com.eCommerce.eCommerceOrder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.eCommerceOrder.entity.FailOverRecordsEntity;


@Repository
public interface FailOverRecordsRepository extends JpaRepository<FailOverRecordsEntity, String>{

}
