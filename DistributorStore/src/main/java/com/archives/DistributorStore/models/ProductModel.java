package com.archives.DistributorStore.models;

import java.util.Set;

import jakarta.persistence.Entity;
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
    private String code_sap;
    private Float price;
    private Integer unit;

    @ManyToOne
    @JoinColumn(name = "catalog_model_id")
    private CatalogModel catalogModel;

    @ManyToMany(mappedBy = "productModels")
    private Set<OrdersModel> ordersModels;
}
