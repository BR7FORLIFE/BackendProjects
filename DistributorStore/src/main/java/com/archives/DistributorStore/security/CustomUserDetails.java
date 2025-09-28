package com.archives.DistributorStore.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.archives.DistributorStore.interfaces.AppUser;

public record CustomUserDetails(
    AppUser appUser

) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUser.getAuthorities();
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }
}
