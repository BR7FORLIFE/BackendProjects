package com.archives.DistributorStore.models;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class OrdersModel {

    @Id
    private Integer id;
    private Double iva_percent;
    private Float price;

    @ManyToMany
    @JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "orders_id"), inverseJoinColumns = @JoinColumn(name = "products_id"))
    private Set<ProductModel> productModels;

    @OneToOne
    @JoinColumn(name = "store_id")
    private StoreModel storeModel;

    @OneToOne
    @JoinColumn(name = "supplier_id")
    private SupplierModel supplierModel;

    @ManyToMany(mappedBy = "ordersModels")
    private List<RutesOfDeliveryModel> rutesOfDeliveryModels;
}
