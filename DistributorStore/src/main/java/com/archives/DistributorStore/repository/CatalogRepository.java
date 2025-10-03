package com.archives.DistributorStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.archives.DistributorStore.models.CatalogModel;

@Repository
public interface CatalogRepository extends JpaRepository<CatalogModel, Integer>{
    
}
