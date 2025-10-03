package com.archives.DistributorStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.RutesOfDeliveryModel;

@Repository
public interface RutesOfDeliveryRepository extends JpaRepository<RutesOfDeliveryModel, Integer>{
    
}
