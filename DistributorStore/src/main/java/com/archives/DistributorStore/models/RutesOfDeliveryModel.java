package com.archives.DistributorStore.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class RutesOfDeliveryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(name = "rutes_of_delivery_and_presales", joinColumns = @JoinColumn(name = "rutes_of_delivery_id"), inverseJoinColumns = @JoinColumn(name = "presales_id"))
    private List<PresalesModel> presalesModels;

    @ManyToMany
    @JoinTable(name = "Rutes_of_delivery_and_vehicles", joinColumns = @JoinColumn(name = "rutes_of_delivery_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<VehicleModel> vehicleModels;

    @OneToMany(mappedBy = "rutesOfDeliveryModels", fetch = FetchType.LAZY)
    private List<OrdersModel> ordersModels;
}
