package com.archives.DistributorStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.VehicleModel;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, Integer>{
    
}
