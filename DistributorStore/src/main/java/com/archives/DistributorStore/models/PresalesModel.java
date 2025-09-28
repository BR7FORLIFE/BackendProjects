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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class PresalesModel implements AppUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String document_type;
    private Integer document_number;
    private Integer phone;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorModel distributorModel;

    @OneToMany(mappedBy = "presalesModel", fetch = FetchType.LAZY)
    private List<OrdersModel> ordersModel;

    @ManyToMany
    @JoinTable(name = "presales_and_rutes_of_presales", joinColumns = @JoinColumn(name = "presales_id"), inverseJoinColumns = @JoinColumn(name = "rutes_of_presales_id"))
    private List<RutesOfPresalesModel> rutesOfPresalesModels;

    @ManyToOne
    @JoinColumn(name = "Catalog_id", referencedColumnName = "id")
    private CatalogModel catalogModel;

    @ManyToMany(mappedBy = "presalesModels", fetch = FetchType.LAZY)
    private List<RutesOfDeliveryModel> rutesOfDeliveryModels;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       return List.of(new SimpleGrantedAuthority(Rols.PRESALE.asAuthority()));
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
