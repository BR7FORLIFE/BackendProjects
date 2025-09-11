package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
    private Integer nit = 1;

    private String name;
    private String direction;
    private String city;
    private Integer phone;
    private String email;

    @OneToOne(mappedBy = "distributorModel", cascade = CascadeType.ALL)
    private CatalogModel catalogModel;

    @OneToMany(mappedBy = "distributorModel", cascade = CascadeType.ALL)
    private List<DeliveryModel> deliverModels;

    @OneToMany(mappedBy = "distributorModel", cascade = CascadeType.ALL)
    private List<SupplierModel> supplierModels;
}
