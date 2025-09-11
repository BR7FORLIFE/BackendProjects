package com.archives.DistributorStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class SupplierModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String document_type;
    private Integer document_number;
    private Integer phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorModel distributorModel;

    @OneToOne(mappedBy = "supplierModel")
    private OrdersModel ordersModel;

    @OneToOne(mappedBy = "supplierModel")
    private RutesOfSupplierModel rutesOfSupplierModel;
}
