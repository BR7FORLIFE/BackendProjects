package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class StoreModel {

    @Id
    private Integer nic;

    private String name;
    private String direction;
    private String city;
    private Integer phone;
    private String email;

    @OneToOne(mappedBy = "storeModel")
    private OrdersModel ordersModel;

    @ManyToMany(mappedBy = "storeModels")
    private List<RutesOfSupplierModel> rutesOfSupplierModels;
}
