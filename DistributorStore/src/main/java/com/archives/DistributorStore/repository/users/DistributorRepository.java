package com.archives.DistributorStore.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archives.DistributorStore.models.DistributorModel;

public interface DistributorRepository extends JpaRepository<DistributorModel, Integer>{
    
}
