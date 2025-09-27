package com.archives.DistributorStore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill_model")
public class BillModel {
    
    @OneToOne(mappedBy = "billModel", fetch = FetchType.EAGER )
    private OrdersModel ordersModel;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreModel storeModel;
}
