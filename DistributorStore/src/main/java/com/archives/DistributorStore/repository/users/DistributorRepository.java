package com.archives.DistributorStore.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.DistributorModel;

@Repository
public interface DistributorRepository extends JpaRepository<DistributorModel, Integer>{
    
}
