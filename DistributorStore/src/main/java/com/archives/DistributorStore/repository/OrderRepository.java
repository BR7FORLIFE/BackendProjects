package com.archives.DistributorStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.OrdersModel;

@Repository
public interface OrderRepository extends JpaRepository<OrdersModel, Integer>{
    
}
