package com.archives.DistributorStore.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archives.DistributorStore.models.StoreModel;

public interface StoreRepository extends JpaRepository<StoreModel, Integer>{
    
}
