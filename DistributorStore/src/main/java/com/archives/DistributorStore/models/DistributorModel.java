package com.archives.DistributorStore.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.archives.DistributorStore.enums.Rols;
import com.archives.DistributorStore.interfaces.AppUser;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class DistributorModel implements AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer nit;
    private String name;
    private String direction;
    private String city;
    private Integer phone;
    private String email;
    private String password;

    @OneToOne(mappedBy = "distributorModel", fetch = FetchType.EAGER)
    private CatalogModel catalogModel;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<DeliveryModel> deliverModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<PresalesModel> presalesModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<ProductModel> productModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<VehicleModel> vehicleModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<RutesOfPresalesModel> rutesOfPresalesModels;

    @OneToMany(mappedBy = "distributorModel", fetch = FetchType.LAZY)
    private List<StoreModel> storeModels;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Rols.DISTRIBUTOR.asAuthority()));
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
