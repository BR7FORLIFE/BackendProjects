package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class StoreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer phone_number;
    private String email;
    private String nic;
    private String direction;
    private String name;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorModel distributorModel;

    @OneToMany(mappedBy = "storeModel", fetch = FetchType.LAZY)
    private List<BillModel> billModels;
}
