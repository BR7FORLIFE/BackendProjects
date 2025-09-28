package com.archives.DistributorStore.repository.users;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archives.DistributorStore.models.DeliveryModel;

public interface DeliveryRepository extends JpaRepository<DeliveryModel, Integer> {

}
