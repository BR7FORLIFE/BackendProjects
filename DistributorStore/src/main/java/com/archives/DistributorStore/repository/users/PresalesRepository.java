package com.archives.DistributorStore.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archives.DistributorStore.models.PresalesModel;

public interface PresalesRepository extends JpaRepository<PresalesModel, Integer>{
    
}
