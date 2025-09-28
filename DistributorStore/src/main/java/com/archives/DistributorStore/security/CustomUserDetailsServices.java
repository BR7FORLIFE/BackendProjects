package com.archives.DistributorStore.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.archives.DistributorStore.repository.users.DeliveryRepository;
import com.archives.DistributorStore.repository.users.DistributorRepository;
import com.archives.DistributorStore.repository.users.PresalesRepository;
import com.archives.DistributorStore.repository.users.StoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServices implements UserDetailsService{

    private final DeliveryRepository deliveryRepository;
    private final DistributorRepository distributorRepository;
    private final PresalesRepository presalesRepository;
    private final StoreRepository storeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
        
}
