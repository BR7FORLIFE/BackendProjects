package com.archives.DistributorStore.enums;

public enum Rols {
    DISTRIBUTOR, DELIVERY, PRESALE, STORE;

    public String asAuthority() {
        return "ROLE_" + name();
    }
}
