package com.archives.DistributorStore.models;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class CatalogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "distributor_id")
    private DistributorModel distributorModel;

    @OneToMany(mappedBy = "catalogModel", fetch = FetchType.LAZY)
    private Set<ProductModel> productModels;

    @OneToMany(mappedBy = "catalogModel", fetch = FetchType.LAZY)
    private List<PresalesModel> presalesModels;

    @OneToMany(mappedBy = "catalogModel", fetch = FetchType.LAZY)
    private List<OrdersModel> ordersModels;
}
