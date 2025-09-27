package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class DistributorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer nit;
    private String name;
    private String direction;
    private String city;
    private Integer phone;
    private String email;

    @OneToOne(mappedBy = "distributorModel", fetch = FetchType.EAGER)
    private CatalogModel catalogModel;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<DeliveryModel> deliverModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<PresalesModel> presalesModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<ProductModel> productModels;

    @OneToMany(mappedBy = "distributorModel",fetch = FetchType.LAZY)
    private List<VehicleModel> vehicleModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<RutesOfPresalesModel> rutesOfPresalesModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<StoreModel> storeModels;
}
