package com.archives.DistributorStore.models;

import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.archives.DistributorStore.enums.OrderDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class OrdersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double iva_percent;
    private Float total_price;

    @Enumerated(EnumType.STRING)
    private OrderDetails orderDetails;

    @ManyToMany
    @JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "orders_id"), inverseJoinColumns = @JoinColumn(name = "products_id"))
    private Set<ProductModel> productModels;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreModel storeModel;

    @ManyToOne
    @JoinColumn(name = "presales_id", referencedColumnName = "id")
    private PresalesModel presalesModel;

    @OneToOne
    @JoinColumn(name = "bill_id")
    private BillModel billModel;

    @ManyToOne
    @JoinColumn(name = "rutes_delivery_id")
    private RutesOfDeliveryModel rutesOfDeliveryModels;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private CatalogModel catalogModel;
}
