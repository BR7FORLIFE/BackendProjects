package com.archives.DistributorStore.models;

import java.util.List;

import com.archives.DistributorStore.enums.DocumentType;
import com.archives.DistributorStore.enums.LicenseType;

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
import lombok.Data;

@Data
@Entity
public class DeliveryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Enumerated(EnumType.STRING)
    private DocumentType document_type;
    private Integer document_number;
    private Integer phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private LicenseType licenseType;

    private Integer license_number;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorModel distributorModel;

    @ManyToMany
    @JoinTable(name = "deliverys_vehicles", joinColumns = @JoinColumn(name = "delivery_model_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<VehicleModel> vehicleModels;
}
