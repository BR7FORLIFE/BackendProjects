package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String vehiclePlate;

    private String brand;
    private String model;
    private String vehicle_type;

    @ManyToMany(mappedBy = "vehicleModels", fetch = FetchType.LAZY)
    private List<DeliveryModel> deliveryModels;

    @ManyToOne
    @JoinColumn(name = "vehicleModels")
    private DistributorModel distributorModel;

    @ManyToMany(mappedBy = "vehicleModels", fetch = FetchType.LAZY)
    private List<RutesOfDeliveryModel> rutesOfDeliveryModels;
}
