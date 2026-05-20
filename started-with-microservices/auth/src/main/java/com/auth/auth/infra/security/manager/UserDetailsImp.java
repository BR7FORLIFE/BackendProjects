package com.auth.auth.infra.security.manager;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.auth.auth.domain.model.UserModel;

public class UserDetailsImp implements UserDetails {
    private final UUID id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> rols;

    public UserDetailsImp(UserModel userModel, Collection<? extends GrantedAuthority> rols) {
        this.id = userModel.getId();
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
        this.rols = rols;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.rols;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public UUID getId() {
        return id;
    }
}
