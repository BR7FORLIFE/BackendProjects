package com.archives.DistributorStore.models;

import java.util.Set;

import com.archives.DistributorStore.enums.Unit;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code_sap;
    private String product_name;
    private Float price;
    private Float quantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "catalog_id", referencedColumnName = "id")
    private CatalogModel catalogModel;

    @ManyToMany(mappedBy = "productModels", fetch = FetchType.LAZY)
    private Set<OrdersModel> ordersModels;

    @ManyToOne
    @JoinColumn(name = "distributor_id", referencedColumnName = "id")
    private DistributorModel distributorModel;
}
