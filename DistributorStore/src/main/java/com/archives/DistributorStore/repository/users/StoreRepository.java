package com.archives.DistributorStore.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.StoreModel;

@Repository
public interface StoreRepository extends JpaRepository<StoreModel, Integer>{
    
}
