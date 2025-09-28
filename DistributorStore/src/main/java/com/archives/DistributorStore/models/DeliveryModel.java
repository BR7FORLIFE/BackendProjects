package com.archives.DistributorStore.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.archives.DistributorStore.enums.DocumentType;
import com.archives.DistributorStore.enums.LicenseType;
import com.archives.DistributorStore.enums.Rols;
import com.archives.DistributorStore.interfaces.AppUser;

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
public class DeliveryModel implements AppUser {

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
    private String password;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorModel distributorModel;

    @ManyToMany
    @JoinTable(name = "deliverys_vehicles", joinColumns = @JoinColumn(name = "delivery_model_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private List<VehicleModel> vehicleModels;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Rols.DELIVERY.asAuthority()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }
}
