package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class RutesOfDeliveryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private DeliveryModel deliveryModel;

    @ManyToMany
    @JoinTable(name = "delivery_orders", joinColumns = @JoinColumn(name = "delivery_rutes_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<OrdersModel> ordersModels;
}
