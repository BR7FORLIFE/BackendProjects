package com.archives.DistributorStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer>{
    
}
