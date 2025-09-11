package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class VehicleModel {
    @Id
    private String license_plate;

    private String brand;
    private String model;
    private String vehicle_type;

    @ManyToMany(mappedBy = "vehicleModels")
    private List<DeliveryModel> deliveryModels;
}
