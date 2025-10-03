package com.archives.DistributorStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.RutesOfPresalesModel;

@Repository
public interface RutesOfPresalesRepository extends JpaRepository<RutesOfPresalesModel, Integer>{
    
}
