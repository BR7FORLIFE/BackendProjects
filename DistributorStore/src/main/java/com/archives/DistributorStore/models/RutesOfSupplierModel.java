package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class RutesOfSupplierModel {
    @Id
    private Integer id;

    @OneToOne
    @JoinTable(name = "supplier_id")
    private PresalesModel supplierModel;

    @ManyToMany
    @JoinTable(name = "supplier_rutes_store", joinColumns = @JoinColumn(name = "supplier_rutes_id"), inverseJoinColumns = @JoinColumn(name = "store_id"))
    private List<StoreModel> storeModels;
}
