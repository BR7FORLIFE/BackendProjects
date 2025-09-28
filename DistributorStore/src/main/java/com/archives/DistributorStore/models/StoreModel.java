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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class StoreModel implements AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer phone_number;
    private String email;
    private String nic;
    private String direction;
    private String name;
    private String password;

    @ManyToOne
    @JoinColumn(name = "distributor_id")
    private DistributorModel distributorModel;

    @OneToMany(mappedBy = "storeModel", fetch = FetchType.LAZY)
    private List<BillModel> billModels;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Rols.STORE.asAuthority()));
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
