package com.archives.DistributorStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.BillModel;

@Repository
public interface BillRepository extends JpaRepository<BillModel, Integer>{
    
}
